package com.BlizzardArmory.connection

import com.BlizzardArmory.model.UserInformation
import com.BlizzardArmory.model.diablo.account.AccountInformation
import com.BlizzardArmory.model.diablo.character.CharacterInformation
import com.BlizzardArmory.model.diablo.item.SingleItem
import com.BlizzardArmory.model.diablo.items.Items
import com.BlizzardArmory.model.starcraft.Player
import com.BlizzardArmory.model.starcraft.profile.Profile
import com.BlizzardArmory.model.warcraft.account.Account
import com.BlizzardArmory.model.warcraft.achievements.DetailedAchievements
import com.BlizzardArmory.model.warcraft.achievements.categories.Categories
import com.BlizzardArmory.model.warcraft.achievements.characterachievements.Achievements
import com.BlizzardArmory.model.warcraft.charactersummary.CharacterSummary
import com.BlizzardArmory.model.warcraft.encounters.EncountersInformation
import com.BlizzardArmory.model.warcraft.equipment.Equipment
import com.BlizzardArmory.model.warcraft.media.Media
import com.BlizzardArmory.model.warcraft.pvp.bracket.BracketStatistics
import com.BlizzardArmory.model.warcraft.pvp.summary.PvPSummary
import com.BlizzardArmory.model.warcraft.pvp.tiers.Tier
import com.BlizzardArmory.model.warcraft.realm.Realms
import com.BlizzardArmory.model.warcraft.reputations.characterreputations.Reputation
import com.BlizzardArmory.model.warcraft.statistic.Statistic
import com.BlizzardArmory.model.warcraft.talents.Talents
import com.BlizzardArmory.model.warcraft.talents.TalentsIcons
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * The interface Network services.
 */
interface NetworkServices {

    @GET("oauth/token")
    fun getAccessToken(@Query("code") clientId: String?,
                       @Query("region") region: String?,
                       @Query("redirect_uri") redirectUri: String?): Call<com.BlizzardArmory.connection.oauth.TokenResponse>

    //User information
    /**
     * Gets user info.
     *
     * @param accessToken the access token
     * @return the user info
     */

    @GET("oauth/userinfo")
    fun getUserInfo(@Query("token") accessToken: String?,
                    @Query("region") region: String?): Call<UserInformation>

    @GET("/data/wow/realm/index")
    fun getRealmIndex(@Query("region") region: String,
                      @Query("namespace") namespace: String,
                      @Query("locale") locale: String,
                      @Query("token") accessToken: String?): Call<Realms>

    //WoW Endpoints
    /**
     * Gets media.
     *
     * @param character   the character
     * @param realm       the realm
     * @param locale      the locale
     * @param accessToken the access token
     * @return the media
     */
    @GET("/profile/wow/character/{realm}/{character}/character-media")
    fun getMedia(@Path("character", encoded = true) character: String?,
                 @Path("realm", encoded = true) realm: String?,
                 @Query("locale") locale: String?,
                 @Query("region") region: String?,
                 @Query("token") accessToken: String?): Call<Media>

    /**
     * Gets dynamic equipment media.
     *
     * @param url         the url
     * @param locale      the locale
     * @return the dynamic equipment media
     */
    @GET
    fun getDynamicEquipmentMedia(@Url url: String?,
                                 @Query("locale") locale: String?,
                                 @Query("region") region: String?): Call<com.BlizzardArmory.model.warcraft.equipment.media.Media>

    /**
     * Gets achievement categories.
     *
     * @return the achievement categories
     */
    @GET
    fun getAchievementCategories(@Url url: String?): Call<Categories>

    /**
     * Gets all achievement.
     *
     * @return all achievement
     */
    @GET
    fun getAllAchievements(@Url url: String?): Call<DetailedAchievements>

    /**
     * Init server.
     *
     */
    @GET
    fun initServer(@Url url: String?): Call<ResponseBody>

    /**
     * Gets character achievements.
     *
     * @param character   the character
     * @param realm       the realm
     * @param locale      the locale
     * @param accessToken the access token
     * @return the character achievements
     */
    @GET("/profile/wow/character/{realm}/{character}/achievements")
    fun getCharacterAchievements(@Path("character", encoded = true) character: String?,
                                 @Path("realm", encoded = true) realm: String?,
                                 @Query("locale") locale: String?,
                                 @Query("region") region: String?,
                                 @Query("token") accessToken: String?): Call<Achievements>

    /**
     * Gets encounters.
     *
     * @param character   the character
     * @param realm       the realm
     * @param locale      the locale
     * @param accessToken the access token
     * @return the encounters
     */
    @GET("profile/wow/character/{realm}/{character}/encounters/raids")
    fun getEncounters(@Path("character", encoded = true) character: String?,
                      @Path("realm", encoded = true) realm: String?,
                      @Query("locale") locale: String?,
                      @Query("region") region: String?,
                      @Query("token") accessToken: String?): Call<EncountersInformation>

    /**
     * Gets equipped items.
     *
     * @param character   the character
     * @param realm       the realm
     * @param locale      the locale
     * @param accessToken the access token
     * @return the equipped items
     */
    @GET("profile/wow/character/{realm}/{character}/equipment")
    fun getEquippedItems(@Path("character", encoded = true) character: String?,
                         @Path("realm", encoded = true) realm: String?,
                         @Query("locale") locale: String?,
                         @Query("region") region: String?,
                         @Query("token") accessToken: String?): Call<Equipment>

    /**
     * Gets stats.
     *
     * @param character   the character
     * @param realm       the realm
     * @param locale      the locale
     * @param accessToken the access token
     * @return the stats
     */
    @GET("profile/wow/character/{realm}/{character}/statistics")
    fun getStats(@Path("character", encoded = true) character: String?,
                 @Path("realm", encoded = true) realm: String?,
                 @Query("locale") locale: String?,
                 @Query("region") region: String?,
                 @Query("token") accessToken: String?): Call<Statistic>

    /**
     * Gets specs.
     *
     * @param character   the character
     * @param realm       the realm
     * @param locale      the locale
     * @param accessToken the access token
     * @return the specs
     */
    @GET("profile/wow/character/{realm}/{character}/specializations")
    fun getSpecs(@Path("character", encoded = true) character: String?,
                 @Path("realm", encoded = true) realm: String?,
                 @Query("locale") locale: String?,
                 @Query("region") region: String?,
                 @Query("token") accessToken: String?): Call<Talents>

    @GET
    fun getTalentsWithIcon(@Url url: String?): Call<List<TalentsIcons>>

    /**
     * Gets character.
     *
     * @param character   the character
     * @param realm       the realm
     * @param locale      the locale
     * @param accessToken the access token
     * @return the character
     */
    @GET("profile/wow/character/{realm}/{character}")
    fun getCharacter(@Path("character", encoded = true) character: String?,
                     @Path("realm", encoded = true) realm: String?,
                     @Query("locale") locale: String?,
                     @Query("region") region: String?,
                     @Query("token") accessToken: String?): Call<CharacterSummary>

    /**
     * Gets account.
     *
     * @param locale      the locale
     * @param accessToken the access token
     * @return the account
     */
    @GET("profile/user/wow")
    fun getAccount(@Query("locale") locale: String?,
                   @Query("region") region: String?,
                   @Query("token") accessToken: String?): Call<Account>

    /**
     * Gets pv p summary.
     *
     * @param character   the character
     * @param realm       the realm
     * @param locale      the locale
     * @param accessToken the access token
     * @return the pv p summary
     */
    @GET("profile/wow/character/{realm}/{character}/pvp-summary")
    fun getPvPSummary(@Path("character", encoded = true) character: String?,
                      @Path("realm", encoded = true) realm: String?,
                      @Query("locale") locale: String?,
                      @Query("region") region: String?,
                      @Query("token") accessToken: String?): Call<PvPSummary>

    /**
     * Gets pv p brackets.
     *
     * @param character   the character
     * @param realm       the realm
     * @param bracket     the bracket
     * @param locale      the locale
     * @param accessToken the access token
     * @return the pv p brackets
     */
    @GET("profile/wow/character/{realm}/{character}/pvp-bracket/{BRACKET}")
    fun getPvPBrackets(@Path("character", encoded = true) character: String?,
                       @Path("realm", encoded = true) realm: String?,
                       @Path("BRACKET") bracket: String?,
                       @Query("locale") locale: String?,
                       @Query("region") region: String?,
                       @Query("token") accessToken: String?): Call<BracketStatistics>

    /**
     * Gets dynamic tier.
     *
     * @param url         the url
     * @param locale      the locale
     * @return the dynamic tier
     */
    @GET
    fun getDynamicTier(@Url url: String?,
                       @Query("region") region: String?,
                       @Query("locale") locale: String?): Call<Tier>

    /**
     * Gets reputations.
     *
     * @param character   the character
     * @param realm       the realm
     * @param locale      the locale
     * @param accessToken the access token
     * @return the reputations
     */
    @GET("profile/wow/character/{realm}/{character}/reputations")
    fun getReputations(@Path("character", encoded = true) character: String?,
                       @Path("realm", encoded = true) realm: String?,
                       @Query("locale") locale: String?,
                       @Query("region") region: String?,
                       @Query("token") accessToken: String?): Call<Reputation>

    //D3 Endpoints
    /**
     * Gets D3 profile.
     *
     * @param battletag   the battletag
     * @param locale      the locale
     * @param accessToken the access token
     * @return the d 3 profile
     */
    @GET("d3/profile/{battletag}/")
    fun getD3Profile(@Path("battletag") battletag: String?,
                     @Query("locale") locale: String?,
                     @Query("region") region: String?,
                     @Query("token") accessToken: String?): Call<AccountInformation>

    /**
     * Gets d 3 hero.
     *
     * @param battletag   the battletag
     * @param id          the id
     * @param locale      the locale
     * @param accessToken the access token
     * @return the d 3 hero
     */
    @GET("d3/profile/{battletag}/hero/{id}")
    fun getD3Hero(@Path("battletag") battletag: String?,
                  @Path("id") id: Long,
                  @Query("locale") locale: String?,
                  @Query("region") region: String?,
                  @Query("token") accessToken: String?): Call<CharacterInformation>

    /**
     * Gets hero items.
     *
     * @param battletag   the battletag
     * @param id          the id
     * @param locale      the locale
     * @param accessToken the access token
     * @return the hero items
     */
    @GET("d3/profile/{battletag}/hero/{id}/items")
    fun getHeroItems(@Path("battletag") battletag: String?,
                     @Path("id") id: Long,
                     @Query("locale") locale: String?,
                     @Query("region") region: String?,
                     @Query("token") accessToken: String?): Call<Items>

    /**
     * Gets item.
     *
     * @param slug        the slug
     * @param locale      the locale
     * @return the item
     */
    @GET("d3/data/item/{slug}")
    fun getItem(@Path("slug", encoded = true) slug: String?,
                @Query("region") region: String?,
                @Query("locale") locale: String?): Call<SingleItem>

    //Sc2 endpoints
    /**
     * Gets sc 2 player.
     *
     * @param id          the id
     * @param locale      the locale
     * @param accessToken the access token
     * @return the sc 2 player
     */
    @GET("sc2/player/{id}")
    fun getSc2Player(@Path("id") id: String?,
                     @Query("locale") locale: String?,
                     @Query("region") region: String?,
                     @Query("token") accessToken: String?): Call<List<Player>>

    /**
     * Gets sc2 profile.
     *
     * @param regionId    the region id
     * @param realmId     the realm id
     * @param profileId   the profile id
     * @param locale      the locale
     * @param accessToken the access token
     * @return the sc 2 profile
     */
    @GET("sc2/profile/{region_id}/{realm_id}/{profile_id}")
    fun getSc2Profile(@Path("region_id") regionId: String,
                      @Path("realm_id") realmId: Int,
                      @Path("profile_id") profileId: String?,
                      @Query("locale") locale: String?,
                      @Query("region") region: String?,
                      @Query("token") accessToken: String?): Call<Profile>

    //Overwatch endpoint
    /**
     * Gets ow profile.
     *
     * @param url the url
     * @return the ow profile
     */
    @GET
    fun getOWProfile(@Url url: String?): Call<com.BlizzardArmory.model.overwatch.Profile>
}