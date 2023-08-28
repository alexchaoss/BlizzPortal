package com.BlizzardArmory.network.services

import com.BlizzardArmory.model.diablo.diablo4.Status
import com.BlizzardArmory.model.diablo.diablo4.account.Account
import com.BlizzardArmory.model.diablo.diablo4.character.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface D4NetworkServices {

    @GET("/status")
    suspend fun getStatus(): Response<Status>

    @GET("api/armory/{btag}")
    suspend fun getAccount(
        @Path("btag") btag: String?,
    ): Response<Account>

    @GET("api/armory/{btag}/{characterId}")
    suspend fun getCharacter(
        @Path("btag") btag: String?,
        @Path("characterId") characterId: String?
    ): Response<Character>
}