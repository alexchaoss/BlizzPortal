package com.BlizzardArmory.ui.auth

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.network.oauth.BattlenetConstants
import com.BlizzardArmory.ui.BaseViewModel
import com.google.api.client.auth.oauth2.TokenResponse
import java.net.URLDecoder
import java.util.Locale

class AuthorizationTokenViewModel(application: Application) : BaseViewModel(application) {

    private var handled = false
    private var hasLoggedIn: MutableLiveData<Boolean> = MutableLiveData()
    private var startActivity: MutableLiveData<Boolean> = MutableLiveData()

    init {
        hasLoggedIn.value = false
    }

    fun isHandled(): Boolean {
        return handled
    }

    fun hasLoggedIn(): LiveData<Boolean> {
        return hasLoggedIn
    }

    fun startActivity(): LiveData<Boolean> {
        return startActivity
    }

    suspend fun processToken(url: String) {
        if (url.startsWith(getBnetParams().value!!.rederictUri)) {
            Log.d(BattlenetConstants.TAG, "Redirect URL found: $url")
            handled = true
            try {
                if (url.contains("code=")) {
                    val authorizationCode = extractCodeFromUrl(url)
                    Log.d(BattlenetConstants.TAG, "Found code = $authorizationCode")
                    val token = retrieveToken(authorizationCode)
                    battlenetOAuth2Helper!!.storeAccessToken(token)
                    startActivity.value = true
                    hasLoggedIn.value = true
                } else if (url.contains("error=") || battlenetOAuth2Helper?.accessToken == null) {
                    startActivity.value = false
                }
            } catch (e: Exception) {
                Log.e(BattlenetConstants.TAG, "Error processing token", e)
            }
        } else {
            Log.d(BattlenetConstants.TAG, "Not doing anything for url $url")
        }
    }

    private suspend fun retrieveToken(authorizationCode: String): TokenResponse {
        Log.d(BattlenetConstants.TAG, "retrieveAndStoreAccessToken for code $authorizationCode")
        val token = TokenResponse()
        val job = executeAPICall({
            RetroClient.getGeneralClient(getApplication()).getAccessToken(
                authorizationCode,
                getBnetParams().value!!.zone.lowercase(Locale.getDefault()),
                getBnetParams().value!!.rederictUri
            )
        },
            {
                val tokenResponse = it.body()
                token.expiresInSeconds = tokenResponse?.expiresIn
                token.accessToken = tokenResponse?.accessToken
                token.scope = tokenResponse?.scope
                token.tokenType = tokenResponse?.tokenType
                Log.d(BattlenetConstants.TAG, "Found tokenResponse: " + token.accessToken)
                if (null != token.accessToken) {
                    Log.d(BattlenetConstants.TAG, "Access Token : " + token.accessToken)
                }
                if (null != token.refreshToken) {
                    Log.d(BattlenetConstants.TAG, "Refresh Token : " + token.refreshToken)
                }
                Log.d("TOKEN", tokenResponse.toString())
            }, { startActivity.value = false })
        job.join()
        return token
    }

    private fun extractCodeFromUrl(url: String): String {
        val encodedCode = url.substring(getBnetParams().value!!.rederictUri.length + 7, url.length)
        return URLDecoder.decode(encodedCode, "UTF-8")
    }
}