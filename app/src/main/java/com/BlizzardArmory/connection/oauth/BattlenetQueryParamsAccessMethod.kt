package com.BlizzardArmory.connection.oauth

import com.google.api.client.auth.oauth2.Credential.AccessMethod
import com.google.api.client.http.HttpRequest

class BattlenetQueryParamsAccessMethod private constructor() : AccessMethod {
    override fun intercept(request: HttpRequest, accessToken: String) {
        request.url[PARAM_NAME] = accessToken
    }

    override fun getAccessTokenFromRequest(request: HttpRequest): String {
        val param = request.url[PARAM_NAME]
        return param.toString()
    }

    companion object {
        private const val PARAM_NAME = "oauth_token"

        @JvmStatic
        val instance = BattlenetQueryParamsAccessMethod()
    }
}