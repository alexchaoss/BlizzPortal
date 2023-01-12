package com.BlizzardArmory.network.services

import com.BlizzardArmory.model.overwatch.account.Profile
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url
import java.io.InputStream

interface OWNetworkServices {

    @GET
    suspend fun getOWProfile(@Url url: String?): Response<Profile>

    @GET
    suspend fun getEndorsementSVG(@Url url: String?): Response<ResponseBody>
}