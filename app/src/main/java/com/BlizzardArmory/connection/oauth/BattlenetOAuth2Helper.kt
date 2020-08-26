package com.BlizzardArmory.connection.oauth

import android.content.SharedPreferences
import android.util.Log
import com.BlizzardArmory.connection.RetroClient
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow
import com.google.api.client.auth.oauth2.CredentialStore
import com.google.api.client.auth.oauth2.TokenResponse
import com.google.api.client.http.GenericUrl
import com.google.api.client.http.HttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import java.io.IOException
import java.util.*


class BattlenetOAuth2Helper(sharedPreferences: SharedPreferences?, oauth2Params: BattlenetOAuth2Params) {
    private val credentialStore: CredentialStore
    private val flow: AuthorizationCodeFlow
    private val oauth2Params: BattlenetOAuth2Params
    val authorizationUrl: String
        get() = flow.newAuthorizationUrl().setRedirectUri(oauth2Params.rederictUri).setScopes(convertScopesToString(oauth2Params.scope)).build()

    @Throws(IOException::class)
    fun retrieveAndStoreAccessToken(authorizationCode: String) {
        Log.i(BattlenetConstants.TAG, "retrieveAndStoreAccessToken for code $authorizationCode")
        val tokenResponse = RetroClient.getClient.getAccessToken(authorizationCode, oauth2Params.zone.toLowerCase(Locale.ROOT), oauth2Params.rederictUri).execute().body()
        Log.i("TOKEN", tokenResponse.toString())
        val token = TokenResponse()
        token.expiresInSeconds = tokenResponse?.expiresIn
        token.accessToken = tokenResponse?.accessToken
        token.scope = tokenResponse?.scope
        token.tokenType = tokenResponse?.tokenType
        Log.i(BattlenetConstants.TAG, "Found tokenResponse: " + token.accessToken)
        if (null != token.accessToken) {
            Log.i(BattlenetConstants.TAG, "Access Token : " + token.accessToken)
        }
        if (null != token.refreshToken) {
            Log.i(BattlenetConstants.TAG, "Refresh Token : " + token.refreshToken)
        }
        flow.createAndStoreCredential(token, oauth2Params.userId)
    }

    @get:Throws(IOException::class)
    val accessToken: String
        get() = flow.loadCredential(oauth2Params.userId).accessToken

    private fun convertScopesToString(scopesConcat: String): Collection<String> {
        val scopes = scopesConcat.split(",").toTypedArray()
        val collection: MutableCollection<String> = ArrayList()
        Collections.addAll(collection, *scopes)
        return collection
    }

    companion object {
        /** Global instance of the HTTP transport.  */
        private val HTTP_TRANSPORT: HttpTransport = NetHttpTransport()

        /** Global instance of the JSON factory.  */
        private val JSON_FACTORY: JsonFactory = JacksonFactory()
    }

    init {
        credentialStore = BattlenetSharedPreferencesCredentialStore(sharedPreferences)
        this.oauth2Params = oauth2Params
        flow = AuthorizationCodeFlow.Builder(oauth2Params.accessMethod, HTTP_TRANSPORT, JSON_FACTORY, GenericUrl(oauth2Params.tokenServerUrl),
                null, oauth2Params.clientId, oauth2Params.authorizationServerEncodedUrl).setCredentialStore(credentialStore).build()
    }
}