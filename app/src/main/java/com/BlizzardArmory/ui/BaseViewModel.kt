package com.BlizzardArmory.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.network.oauth.BattlenetConstants
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.util.IdGenarator
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import retrofit2.Response

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    var jobs = mutableMapOf<String, Job>()
    private var clientID: String? = "339a9ad89d9f4acf889b025ccb439567"

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("Error", throwable.localizedMessage, throwable)
    }

    val coroutineScope = CoroutineScope(Dispatchers.IO + exceptionHandler)

    var battlenetOAuth2Helper: BattlenetOAuth2Helper? = null
    private var battlenetOAuth2Params: MutableLiveData<BattlenetOAuth2Params> = MutableLiveData()

    var errorCode: MutableLiveData<Int> = MutableLiveData()
    var showErrorDialog: MutableLiveData<Boolean> = MutableLiveData()

    init {
        EventBus.getDefault().register(this)
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    open fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        EventBus.getDefault().unregister(this)
    }

    fun getBnetParams(): MutableLiveData<BattlenetOAuth2Params> {
        return battlenetOAuth2Params
    }

    fun setBnetParams(bnetParams: BattlenetOAuth2Params) {
        battlenetOAuth2Params.value = bnetParams
    }

    fun openLoginToBattleNet() {
        battlenetOAuth2Params.value = BattlenetOAuth2Params(
            clientID!!,
            NetworkUtils.region,
            NetworkUtils.CALLBACK_URL,
            "BlizzPortal",
            BattlenetConstants.SCOPE_WOW,
            BattlenetConstants.SCOPE_SC2
        )
    }

    fun getErrorCode(): LiveData<Int> {
        return errorCode
    }

    fun getShowErrorDialog(): LiveData<Boolean> {
        return showErrorDialog
    }

    fun <T> executeAPICall(
        call: suspend () -> Response<T>,
        onResponse: (response: Response<T>) -> Unit = {},
        onError: (response: Response<T>) -> Unit = {},
        onCatch: (exception: Exception) -> Unit = {},
        onComplete: () -> Unit = {},
        tries: Int = 0,
        jobName: String = "",
    ): Job {
        val currentJobName = jobName.ifEmpty { IdGenarator.generate(10) }
        val job = coroutineScope.launch {
            coroutineScope.launch {
                try {
                    val response = call()
                    withContext(Dispatchers.Main) {
                        if (response.isSuccessful) {
                            onResponse(response)
                        } else {
                            errorCode.value = response.code()
                            onError(response)
                            Log.e("Error", "Code: ${response.code()} Message: ${response.message()}, ${response.body()}")
                        }
                    }
                } catch (e: Exception) {
                    Log.e("Exception", "Message: ${e.message}\n ${e.printStackTrace()}")
                    if(e.message?.lowercase() == "timeout" && tries < 5) {
                        delay(1000)
                        executeAPICall(call, onResponse, onError, onCatch, onComplete, tries + 1, currentJobName + tries + 1)
                    } else {
                        jobs.filter { it.key.contains(currentJobName) }.forEach { it.value.cancel() }
                        onCatch(e)
                    }
                }
            }.join()
            withContext(Dispatchers.Main) {
                onComplete()
            }
        }
        jobs[currentJobName] = job
        return job
    }

    override fun onCleared() {
        super.onCleared()
        EventBus.getDefault().unregister(this)
        jobs.forEach { it.value.cancel() }
    }
}