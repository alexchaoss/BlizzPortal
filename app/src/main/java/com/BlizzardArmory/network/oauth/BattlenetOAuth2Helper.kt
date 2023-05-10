package com.BlizzardArmory.network.oauth

import android.util.Log
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow
import com.google.api.client.auth.oauth2.StoredCredential
import com.google.api.client.auth.oauth2.TokenResponse
import com.google.api.client.http.GenericUrl
import com.google.api.client.http.HttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.MemoryDataStoreFactory
import java.io.IOException
import java.util.*


class BattlenetOAuth2Helper(private val oauth2Params: BattlenetOAuth2Params) {
    private val flow: AuthorizationCodeFlow
    val authorizationUrl: String
        get() = flow.newAuthorizationUrl().setRedirectUri(oauth2Params.rederictUri)
            .setScopes(convertScopesToString(oauth2Params.scope)).build()

    fun storeAccessToken(token: TokenResponse) {
        Log.d("TOKEN", "$token")
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
        flow = AuthorizationCodeFlow.Builder(oauth2Params.accessMethod, HTTP_TRANSPORT, JSON_FACTORY, GenericUrl(oauth2Params.tokenServerUrl),
            null, oauth2Params.clientId, oauth2Params.authorizationServerEncodedUrl)
            .setCredentialDataStore(StoredCredential.getDefaultDataStore(MemoryDataStoreFactory.getDefaultInstance()))
            .build()
    }
}