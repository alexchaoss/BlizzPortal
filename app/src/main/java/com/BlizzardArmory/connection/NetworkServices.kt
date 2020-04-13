package com.BlizzardArmory.connection

import com.BlizzardArmory.UserInformation
import com.BlizzardArmory.diablo.account.AccountInformation
import com.BlizzardArmory.diablo.character.CharacterInformation
import com.BlizzardArmory.diablo.item.SingleItem
import com.BlizzardArmory.diablo.items.Items
import com.BlizzardArmory.starcraft.Player
import com.BlizzardArmory.starcraft.profile.Profile
import com.BlizzardArmory.warcraft.account.Account
import com.BlizzardArmory.warcraft.charactersummary.CharacterSummary
import com.BlizzardArmory.warcraft.encounters.EncountersInformation
import com.BlizzardArmory.warcraft.equipment.Equipment
import com.BlizzardArmory.warcraft.media.Media
import com.BlizzardArmory.warcraft.pvp.bracket.BracketStatistics
import com.BlizzardArmory.warcraft.pvp.summary.PvPSummary
import com.BlizzardArmory.warcraft.pvp.tiers.Tier
import com.BlizzardArmory.warcraft.reputations.characterreputations.Reputation
import com.BlizzardArmory.warcraft.statistic.Statistic
import com.BlizzardArmory.warcraft.talents.Talents
import com.google.api.client.auth.oauth2.TokenResponse
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
                       @Query("redirect_uri") redirectUri: String?): Call<TokenResponse>

    /**
     * Gets user info.
     *
     * @param accessToken the access token
     * @return the user info
     */
    //User information
    @GET("oauth/userinfo")
    fun getUserInfo(@Query("token") accessToken: String?,
                    @Query("region") region: String?): Call<UserInformation>

    /**
     * Gets media.
     *
     * @param character   the character
     * @param realm       the realm
     * @param locale      the locale
     * @param accessToken the access token
     * @return the media
     */
    //WoW Endpoints
    @GET("/profile/wow/character/{realm}/{character}/character-media")
    fun getMedia(@Path("character") character: String?,
                 @Path("realm") realm: String?,
                 @Query("locale") locale: String?,
                 @Query("token") accessToken: String?): Call<Media>

    /**
     * Gets dynamic equipment media.
     *
     * @param url         the url
     * @param locale      the locale
     * @param accessToken the access token
     * @return the dynamic equipment media
     */
    @GET
    fun getDynamicEquipmentMedia(@Url url: String?,
                                 @Query("locale") locale: String?,
                                 @Query("token") accessToken: String?): Call<com.BlizzardArmory.warcraft.equipment.media.Media>

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
    fun getEncounters(@Path("character") character: String?,
                      @Path("realm") realm: String?,
                      @Query("locale") locale: String?,
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
    fun getEquippedItems(@Path("character") character: String?,
                         @Path("realm") realm: String?,
                         @Query("locale") locale: String?,
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
    fun getStats(@Path("character") character: String?,
                 @Path("realm") realm: String?,
                 @Query("locale") locale: String?,
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
    fun getSpecs(@Path("character") character: String?,
                 @Path("realm") realm: String?,
                 @Query("locale") locale: String?,
                 @Query("token") accessToken: String?): Call<Talents>

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
    fun getCharacter(@Path("character") character: String?,
                     @Path("realm") realm: String?,
                     @Query("locale") locale: String?,
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
    fun getPvPSummary(@Path("character") character: String?,
                      @Path("realm") realm: String?,
                      @Query("locale") locale: String?,
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
    fun getPvPBrackets(@Path("character") character: String?,
                       @Path("realm") realm: String?,
                       @Path("BRACKET") bracket: String?,
                       @Query("locale") locale: String?,
                       @Query("token") accessToken: String?): Call<BracketStatistics>

    /**
     * Gets dynamic tier.
     *
     * @param url         the url
     * @param locale      the locale
     * @param accessToken the access token
     * @return the dynamic tier
     */
    @GET
    fun getDynamicTier(@Url url: String?,
                       @Query("locale") locale: String?,
                       @Query("token") accessToken: String?): Call<Tier>

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
    fun getReputations(@Path("character") character: String?,
                       @Path("realm") realm: String?,
                       @Query("locale") locale: String?,
                       @Query("token") accessToken: String?): Call<Reputation>
    //D3 Endpoints
    /**
     * Gets d 3 profile.
     *
     * @param battletag   the battletag
     * @param locale      the locale
     * @param accessToken the access token
     * @return the d 3 profile
     */
    @GET("d3/profile/{battletag}/")
    fun getD3Profile(@Path("battletag") battletag: String?,
                     @Query("locale") locale: String?,
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
                     @Query("token") accessToken: String?): Call<Items>

    /**
     * Gets item.
     *
     * @param item        the item
     * @param locale      the locale
     * @param accessToken the access token
     * @return the item
     */
    @GET("d3/data/{item}")
    fun getItem(@Path("item") item: String?,
                @Query("locale") locale: String?,
                @Query("token") accessToken: String?): Call<SingleItem>

    /**
     * Gets sc 2 player.
     *
     * @param id          the id
     * @param locale      the locale
     * @param accessToken the access token
     * @return the sc 2 player
     */
    //Sc2 endpoints
    @GET("sc2/player/{id}")
    fun getSc2Player(@Path("id") id: String?,
                     @Query("locale") locale: String?,
                     @Query("token") accessToken: String?): Call<List<Player>>

    /**
     * Gets sc 2 profile.
     *
     * @param regionId    the region id
     * @param realmId     the realm id
     * @param profileId   the profile id
     * @param locale      the locale
     * @param accessToken the access token
     * @return the sc 2 profile
     */
    @GET("sc2/profile/{region_id}/{realm_id}/{profile_id}")
    fun getSc2Profile(@Path("region_id") regionId: Int,
                      @Path("realm_id") realmId: Int,
                      @Path("profile_id") profileId: String?,
                      @Query("locale") locale: String?,
                      @Query("token") accessToken: String?): Call<Profile>

    /**
     * Gets ow profile.
     *
     * @param url the url
     * @return the ow profile
     */
    //Overwatch endpoint
    @GET
    fun getOWProfile(@Url url: String?): Call<com.BlizzardArmory.overwatch.Profile>
}