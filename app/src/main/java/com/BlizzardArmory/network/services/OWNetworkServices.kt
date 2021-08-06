package com.BlizzardArmory.network.services

import com.BlizzardArmory.model.overwatch.account.Profile
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface OWNetworkServices {

    @GET
    suspend fun getOWProfile(@Url url: String?): Response<Profile>
}