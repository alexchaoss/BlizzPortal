package com.BlizzardArmory.network.services

import com.BlizzardArmory.model.Status
import com.BlizzardArmory.model.UserInformation
import com.BlizzardArmory.network.oauth.TokenResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface GeneralNetworkServices {

    @GET("/")
    suspend fun getRoot(): Response<Status>

    @GET("/oauth/token")
    suspend fun getAccessToken(
        @Query("code") clientId: String?,
        @Query("region") region: String?,
        @Query("redirect_uri") redirectUri: String?
    ): Response<TokenResponse>

    @GET("/oauth/userinfo")
    suspend fun getUserInfo(
        @Query("token") accessToken: String?,
        @Query("region") region: String?
    ): Response<UserInformation>

    @GET
    suspend fun initWoWServer(@Url url: String?): Response<String>
}