package com.BlizzardArmory.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Params
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

open class BaseViewModel : ViewModel() {
    var jobs = arrayListOf<Job>()
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e("Error", throwable.localizedMessage, throwable)
    }
    val coroutineScope = CoroutineScope(Dispatchers.IO + exceptionHandler)

    var battlenetOAuth2Helper: BattlenetOAuth2Helper? = null
    private var battlenetOAuth2Params: MutableLiveData<BattlenetOAuth2Params> = MutableLiveData()

    var errorCode: MutableLiveData<Int> = MutableLiveData()

    fun getBnetParams(): MutableLiveData<BattlenetOAuth2Params> {
        return battlenetOAuth2Params
    }

    fun getErrorCode(): LiveData<Int> {
        return errorCode
    }

    override fun onCleared() {
        super.onCleared()
        jobs.forEach { it.cancel() }
    }
}