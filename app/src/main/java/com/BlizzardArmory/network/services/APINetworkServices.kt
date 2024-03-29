package com.BlizzardArmory.network.services

import com.BlizzardArmory.model.warcraft.achievements.categories.Categories
import com.BlizzardArmory.model.warcraft.achievements.custom.DetailedAchievements
import com.BlizzardArmory.model.warcraft.covenant.covenant.custom.CovenantSpells
import com.BlizzardArmory.model.warcraft.mythicplusleaderboards.expansion.Expansion
import com.BlizzardArmory.model.warcraft.reputations.custom.ReputationPlusParentInfo
import com.BlizzardArmory.model.warcraft.specialization.Specialization
import com.BlizzardArmory.network.NetworkUtils
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface APINetworkServices {

    @GET("/categories/{locale}")
    suspend fun getAchievementCategories(@Path("locale") locale: String = NetworkUtils.locale): Response<Categories>

    @GET("/achievements/{locale}")
    suspend fun getAllAchievements(@Path("locale") locale: String = NetworkUtils.locale): Response<DetailedAchievements>

    @GET("/covenant/{covenantId}/{locale}")
    suspend fun getCovenantSpells(@Path("covenantId") covenantId: Int,
                                  @Path("locale") locale: String = NetworkUtils.locale): Response<List<CovenantSpells>>

    @GET("/covenant/class/{playableClassId}/{locale}")
    suspend fun getCovenantClassSpells(@Path("playableClassId") playableClassId: Int,
                                       @Path("locale") locale: String = NetworkUtils.locale): Response<List<CovenantSpells>>

    @GET("/reputations/{locale}")
    suspend fun getReputationPlusParentInfo(@Path("locale") locale: String = NetworkUtils.locale): Response<List<ReputationPlusParentInfo>>

    @GET("/playableSpecialization")
    suspend fun getAllPlayableSpecializations(): Response<List<Specialization>>

    //Raider.IO
    @GET
    suspend fun getExpansion(@Url url: String): Response<Expansion>
}