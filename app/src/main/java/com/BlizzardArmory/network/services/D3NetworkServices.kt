package com.BlizzardArmory.network.services

import com.BlizzardArmory.model.diablo.account.AccountInformation
import com.BlizzardArmory.model.diablo.character.CharacterInformation
import com.BlizzardArmory.model.diablo.data.common.Leaderboard
import com.BlizzardArmory.model.diablo.data.eras.index.EraIndex
import com.BlizzardArmory.model.diablo.data.seasons.index.SeasonIndex
import com.BlizzardArmory.model.diablo.item.SingleItem
import com.BlizzardArmory.model.diablo.items.Items
import com.BlizzardArmory.network.NetworkUtils
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface D3NetworkServices {

    @GET("/d3/profile/{battletag}/")
    suspend fun getD3Profile(
        @Path("battletag") battletag: String?,
        @Query("token") accessToken: String?,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<AccountInformation>

    @GET("/d3/profile/{battletag}/hero/{id}")
    suspend fun getD3Hero(
        @Path("battletag") battletag: String?,
        @Path("id") id: Long,
        @Query("token") accessToken: String?,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<CharacterInformation>

    @GET("/d3/profile/{battletag}/hero/{id}/items")
    suspend fun getHeroItems(
        @Path("battletag") battletag: String?,
        @Path("id") id: Long,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<Items>

    @GET("/d3/data/item/{slug}")
    suspend fun getItem(
        @Path("slug", encoded = true) slug: String?,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<SingleItem>

    @GET("/data/d3/season/")
    suspend fun getSeasonIndex(
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<SeasonIndex>

    @GET("/data/d3/season/{id}/leaderboard/{leaderboard}")
    suspend fun getSeasonLeaderboard(
        @Path("id") id: Int,
        @Path("leaderboard") leaderboard: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<Leaderboard>

    @GET("/data/d3/era/")
    suspend fun getEraIndex(
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<EraIndex>

    @GET("/data/d3/era/{id}/leaderboard/{leaderboard}")
    suspend fun getEraLeaderboard(
        @Path("id") id: Int,
        @Path("leaderboard") leaderboard: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<Leaderboard>

}