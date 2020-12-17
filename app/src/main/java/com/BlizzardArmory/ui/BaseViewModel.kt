package com.BlizzardArmory.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

open class BaseViewModel : ViewModel() {
    var jobs = arrayListOf<Job>()

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e("Error", throwable.localizedMessage, throwable)
    }

    val coroutineScope = CoroutineScope(Dispatchers.IO + exceptionHandler)

    var battlenetOAuth2Helper: BattlenetOAuth2Helper? = null
    private var battlenetOAuth2Params: MutableLiveData<BattlenetOAuth2Params> = MutableLiveData()

    var errorCode: MutableLiveData<Int> = MutableLiveData()

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

    fun getErrorCode(): LiveData<Int> {
        return errorCode
    }

    override fun onCleared() {
        super.onCleared()
        EventBus.getDefault().unregister(this)
        jobs.forEach { it.cancel() }
    }
}