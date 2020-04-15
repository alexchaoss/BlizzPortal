package com.BlizzardArmory.connection.oauth

import android.content.SharedPreferences
import android.util.Log
import com.BlizzardArmory.connection.RetroClient
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow
import com.google.api.client.auth.oauth2.CredentialStore
import com.google.api.client.http.GenericUrl
import com.google.api.client.http.HttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import java.io.IOException
import java.util.*


class BnOAuth2Helper(sharedPreferences: SharedPreferences?, oauth2Params: BnOAuth2Params) {
    private val credentialStore: CredentialStore
    private val flow: AuthorizationCodeFlow
    private val oauth2Params: BnOAuth2Params
    val authorizationUrl: String
        get() = flow.newAuthorizationUrl().setRedirectUri(oauth2Params.rederictUri).setScopes(convertScopesToString(oauth2Params.scope)).build()

    @Throws(IOException::class)
    fun retrieveAndStoreAccessToken(authorizationCode: String) {
        Log.i(BnConstants.TAG, "retrieveAndStoreAccessToken for code $authorizationCode")
        val tokenResponse = RetroClient.getClient.getAccessToken(authorizationCode, oauth2Params.zone.toLowerCase(Locale.ROOT), oauth2Params.rederictUri).execute().body()
        //val tokenResponse: TokenResponse = gson?.fromJson(call, TokenResponse::class.java)!!
        Log.i(BnConstants.TAG, "Found tokenResponse: " + tokenResponse?.accessToken)
        if (null != tokenResponse?.accessToken) {
            Log.i(BnConstants.TAG, "Access Token : " + tokenResponse.accessToken)
        }
        if (null != tokenResponse?.refreshToken) {
            Log.i(BnConstants.TAG, "Refresh Token : " + tokenResponse.refreshToken)
        }
        flow.createAndStoreCredential(tokenResponse, oauth2Params.userId)
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
        credentialStore = BnSharedPreferencesCredentialStore(sharedPreferences)
        this.oauth2Params = oauth2Params
        flow = AuthorizationCodeFlow.Builder(oauth2Params.accessMethod, HTTP_TRANSPORT, JSON_FACTORY, GenericUrl(oauth2Params.tokenServerUrl),
                null, oauth2Params.clientId, oauth2Params.authorizationServerEncodedUrl).setCredentialStore(credentialStore).build()
    }
}