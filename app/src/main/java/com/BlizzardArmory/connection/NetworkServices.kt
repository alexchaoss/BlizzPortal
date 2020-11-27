package com.BlizzardArmory.connection

import com.BlizzardArmory.model.UserInformation
import com.BlizzardArmory.model.diablo.account.AccountInformation
import com.BlizzardArmory.model.diablo.character.CharacterInformation
import com.BlizzardArmory.model.diablo.data.common.Leaderboard
import com.BlizzardArmory.model.diablo.data.eras.era.Era
import com.BlizzardArmory.model.diablo.data.eras.index.EraIndex
import com.BlizzardArmory.model.diablo.data.seasons.index.SeasonIndex
import com.BlizzardArmory.model.diablo.data.seasons.season.Season
import com.BlizzardArmory.model.diablo.item.SingleItem
import com.BlizzardArmory.model.diablo.items.Items
import com.BlizzardArmory.model.starcraft.Player
import com.BlizzardArmory.model.starcraft.profile.Profile
import com.BlizzardArmory.model.warcraft.account.Account
import com.BlizzardArmory.model.warcraft.achievements.categories.Categories
import com.BlizzardArmory.model.warcraft.achievements.characterachievements.Achievements
import com.BlizzardArmory.model.warcraft.achievements.custom.DetailedAchievements
import com.BlizzardArmory.model.warcraft.charactersummary.CharacterSummary
import com.BlizzardArmory.model.warcraft.covenant.character.soulbind.SoulbindInformation
import com.BlizzardArmory.model.warcraft.covenant.covenant.custom.CovenantSpells
import com.BlizzardArmory.model.warcraft.encounters.EncountersInformation
import com.BlizzardArmory.model.warcraft.equipment.Equipment
import com.BlizzardArmory.model.warcraft.media.Media
import com.BlizzardArmory.model.warcraft.pvp.bracket.BracketStatistics
import com.BlizzardArmory.model.warcraft.pvp.summary.PvPSummary
import com.BlizzardArmory.model.warcraft.pvp.tiers.Tier
import com.BlizzardArmory.model.warcraft.realm.Realms
import com.BlizzardArmory.model.warcraft.reputations.characterreputations.Reputation
import com.BlizzardArmory.model.warcraft.reputations.custom.ReputationPlusParentInfo
import com.BlizzardArmory.model.warcraft.statistic.Statistic
import com.BlizzardArmory.model.warcraft.talents.Talents
import com.BlizzardArmory.model.warcraft.talents.TalentsIcons
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface NetworkServices {

    @GET("oauth/token")
    fun getAccessToken(@Query("code") clientId: String?,
                       @Query("region") region: String?,
                       @Query("redirect_uri") redirectUri: String?): Call<com.BlizzardArmory.connection.oauth.TokenResponse>

    //User information
    @GET("oauth/userinfo")
    fun getUserInfo(@Query("token") accessToken: String?,
                    @Query("region") region: String?): Call<UserInformation>

    @GET("/data/wow/realm/index")
    fun getRealmIndex(@Query("region") region: String,
                      @Query("namespace") namespace: String,
                      @Query("locale") locale: String,
                      @Query("token") accessToken: String?): Call<Realms>

    //WoW Endpoints
    @GET("/profile/wow/character/{realm}/{character}/character-media")
    fun getMedia(@Path("character", encoded = true) character: String?,
                 @Path("realm", encoded = true) realm: String?,
                 @Query("locale") locale: String?,
                 @Query("region") region: String?,
                 @Query("token") accessToken: String?): Call<Media>

    @GET
    fun getDynamicEquipmentMedia(@Url url: String?,
                                 @Query("locale") locale: String?,
                                 @Query("region") region: String?): Call<com.BlizzardArmory.model.warcraft.equipment.media.Media>

    @GET
    fun getAchievementCategories(@Url url: String?): Call<Categories>

    @GET
    fun getAllAchievements(@Url url: String?): Call<DetailedAchievements>

    @GET
    fun getTalentsWithIcon(@Url url: String?): Call<List<TalentsIcons>>

    @GET
    fun getCovenantSpells(@Url url: String): Call<List<CovenantSpells>>

    @GET
    fun getReputationPlusParentInfo(@Url url: String): Call<List<ReputationPlusParentInfo>>

    @GET("/profile/wow/character/{realm}/{character}/achievements")
    fun getCharacterAchievements(@Path("character", encoded = true) character: String?,
                                 @Path("realm", encoded = true) realm: String?,
                                 @Query("locale") locale: String?,
                                 @Query("region") region: String?,
                                 @Query("token") accessToken: String?): Call<Achievements>

    @GET("profile/wow/character/{realm}/{character}/encounters/raids")
    fun getEncounters(@Path("character", encoded = true) character: String?,
                      @Path("realm", encoded = true) realm: String?,
                      @Query("locale") locale: String?,
                      @Query("region") region: String?,
                      @Query("token") accessToken: String?): Call<EncountersInformation>

    @GET("profile/wow/character/{realm}/{character}/equipment")
    fun getEquippedItems(@Path("character", encoded = true) character: String?,
                         @Path("realm", encoded = true) realm: String?,
                         @Query("locale") locale: String?,
                         @Query("region") region: String?,
                         @Query("token") accessToken: String?): Call<Equipment>

    @GET("profile/wow/character/{realm}/{character}/statistics")
    fun getStats(@Path("character", encoded = true) character: String?,
                 @Path("realm", encoded = true) realm: String?,
                 @Query("locale") locale: String?,
                 @Query("region") region: String?,
                 @Query("token") accessToken: String?): Call<Statistic>

    @GET("profile/wow/character/{realm}/{character}/specializations")
    fun getSpecs(@Path("character", encoded = true) character: String?,
                 @Path("realm", encoded = true) realm: String?,
                 @Query("locale") locale: String?,
                 @Query("region") region: String?,
                 @Query("token") accessToken: String?): Call<Talents>


    @GET("profile/wow/character/{realm}/{character}")
    fun getCharacter(@Path("character", encoded = true) character: String?,
                     @Path("realm", encoded = true) realm: String?,
                     @Query("locale") locale: String?,
                     @Query("region") region: String?,
                     @Query("token") accessToken: String?): Call<CharacterSummary>

    @GET("profile/user/wow")
    fun getAccount(@Query("locale") locale: String?,
                   @Query("region") region: String?,
                   @Query("token") accessToken: String?): Call<Account>

    @GET("profile/wow/character/{realm}/{character}/pvp-summary")
    fun getPvPSummary(@Path("character", encoded = true) character: String?,
                      @Path("realm", encoded = true) realm: String?,
                      @Query("locale") locale: String?,
                      @Query("region") region: String?,
                      @Query("token") accessToken: String?): Call<PvPSummary>

    @GET("profile/wow/character/{realm}/{character}/pvp-bracket/{BRACKET}")
    fun getPvPBrackets(@Path("character", encoded = true) character: String?,
                       @Path("realm", encoded = true) realm: String?,
                       @Path("BRACKET") bracket: String?,
                       @Query("locale") locale: String?,
                       @Query("region") region: String?,
                       @Query("token") accessToken: String?): Call<BracketStatistics>

    @GET
    fun getDynamicTier(@Url url: String?,
                       @Query("region") region: String?,
                       @Query("locale") locale: String?): Call<Tier>

    @GET("profile/wow/character/{realm}/{character}/reputations")
    fun getReputations(@Path("character", encoded = true) character: String?,
                       @Path("realm", encoded = true) realm: String?,
                       @Query("locale") locale: String?,
                       @Query("region") region: String?,
                       @Query("token") accessToken: String?): Call<Reputation>

    @GET("/profile/wow/character/{realm}/{character}/soulbinds")
    fun getSoulbinds(@Path("character", encoded = true) character: String?,
                     @Path("realm", encoded = true) realm: String?,
                     @Query("locale") locale: String?,
                     @Query("region") region: String?,
                     @Query("token") accessToken: String?): Call<SoulbindInformation>

    //D3 Endpoints
    @GET("d3/profile/{battletag}/")
    fun getD3Profile(@Path("battletag") battletag: String?,
                     @Query("locale") locale: String?,
                     @Query("region") region: String?,
                     @Query("token") accessToken: String?): Call<AccountInformation>

    @GET("d3/profile/{battletag}/hero/{id}")
    fun getD3Hero(@Path("battletag") battletag: String?,
                  @Path("id") id: Long,
                  @Query("locale") locale: String?,
                  @Query("region") region: String?,
                  @Query("token") accessToken: String?): Call<CharacterInformation>

    @GET("d3/profile/{battletag}/hero/{id}/items")
    fun getHeroItems(@Path("battletag") battletag: String?,
                     @Path("id") id: Long,
                     @Query("locale") locale: String?,
                     @Query("region") region: String?,
                     @Query("token") accessToken: String?): Call<Items>

    @GET("d3/data/item/{slug}")
    fun getItem(@Path("slug", encoded = true) slug: String?,
                @Query("region") region: String?,
                @Query("locale") locale: String?): Call<SingleItem>

    @GET("data/d3/season/")
    fun getSeasonIndex(@Query("locale") locale: String,
                       @Query("region") region: String): Call<SeasonIndex>

    @GET("data/d3/season/{id}")
    fun getSeasonById(@Path("id") id: Int,
                      @Query("locale") locale: String,
                      @Query("region") region: String): Call<Season>

    @GET("data/d3/season/{id}/leaderboard/{leaderboard}")
    fun getSeasonLeaderboard(@Path("id") id: Int,
                             @Path("leaderboard") leaderboard: String,
                             @Query("locale") locale: String,
                             @Query("region") region: String): Call<Leaderboard>

    @GET("data/d3/era/")
    fun getEraIndex(@Query("locale") locale: String,
                    @Query("region") region: String): Call<EraIndex>

    @GET("data/d3/era/{id}")
    fun getEraById(@Path("id") id: Int,
                   @Query("locale") locale: String,
                   @Query("region") region: String): Call<Era>

    @GET("data/d3/era/{id}/leaderboard/{leaderboard}")
    fun getEraLeaderboard(@Path("id") id: Int,
                          @Path("leaderboard") leaderboard: String,
                          @Query("locale") locale: String,
                          @Query("region") region: String): Call<Leaderboard>

    //Sc2 endpoints
    @GET("sc2/player/{id}")
    fun getSc2Player(@Path("id") id: String?,
                     @Query("locale") locale: String?,
                     @Query("region") region: String?,
                     @Query("token") accessToken: String?): Call<List<Player>>

    @GET("sc2/profile/{region_id}/{realm_id}/{profile_id}")
    fun getSc2Profile(@Path("region_id") regionId: String,
                      @Path("realm_id") realmId: Int,
                      @Path("profile_id") profileId: String?,
                      @Query("locale") locale: String?,
                      @Query("region") region: String?,
                      @Query("token") accessToken: String?): Call<Profile>

    //Overwatch endpoint
    @GET
    fun getOWProfile(@Url url: String?): Call<com.BlizzardArmory.model.overwatch.Profile>
}