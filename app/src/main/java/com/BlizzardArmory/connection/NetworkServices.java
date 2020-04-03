package com.BlizzardArmory.connection;

import com.BlizzardArmory.UserInformation;
import com.BlizzardArmory.diablo.account.AccountInformation;
import com.BlizzardArmory.diablo.character.CharacterInformation;
import com.BlizzardArmory.diablo.item.SingleItem;
import com.BlizzardArmory.diablo.items.Items;
import com.BlizzardArmory.starcraft.Player;
import com.BlizzardArmory.starcraft.profile.Profile;
import com.BlizzardArmory.warcraft.account.Account;
import com.BlizzardArmory.warcraft.charactersummary.CharacterSummary;
import com.BlizzardArmory.warcraft.encounters.EncountersInformation;
import com.BlizzardArmory.warcraft.equipment.Equipment;
import com.BlizzardArmory.warcraft.media.Media;
import com.BlizzardArmory.warcraft.pvp.bracket.BracketStatistics;
import com.BlizzardArmory.warcraft.pvp.summary.PvPSummary;
import com.BlizzardArmory.warcraft.pvp.tiers.Tier;
import com.BlizzardArmory.warcraft.reputations.characterreputations.Reputation;
import com.BlizzardArmory.warcraft.statistic.Statistic;
import com.BlizzardArmory.warcraft.talents.Talents;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface NetworkServices {

    //User information
    @GET("oauth/userinfo")
    Call<UserInformation> getUserInfo(@Query("access_token") String accessToken);

    //WoW Endpoints
    @GET("/profile/wow/character/{realm}/{charactername}/character-media")
    Call<Media> getMedia(@Path("charactername") String character,
                         @Path("realm") String realm,
                         @Query("namespace") String namespace,
                         @Query("locale") String locale,
                         @Query("access_token") String accessToken);

    @GET
    Call<com.BlizzardArmory.warcraft.equipment.media.Media> getDynamicEquipmentMedia(@Url String url,
                                                                                     @Query("locale") String locale,
                                                                                     @Query("access_token") String accessToken);

    @GET("profile/wow/character/{realm}/{charactername}/encounters/raids")
    Call<EncountersInformation> getEncounters(@Path("charactername") String character,
                                              @Path("realm") String realm,
                                              @Query("namespace") String namespace,
                                              @Query("locale") String locale,
                                              @Query("access_token") String accessToken);

    @GET("profile/wow/character/{realm}/{charactername}/equipment")
    Call<Equipment> getEquippedItems(@Path("charactername") String character,
                                     @Path("realm") String realm,
                                     @Query("namespace") String namespace,
                                     @Query("locale") String locale,
                                     @Query("access_token") String accessToken);

    @GET("profile/wow/character/{realm}/{charactername}/statistics")
    Call<Statistic> getStats(@Path("charactername") String character,
                             @Path("realm") String realm,
                             @Query("namespace") String namespace,
                             @Query("locale") String locale,
                             @Query("access_token") String accessToken);

    @GET("profile/wow/character/{realm}/{charactername}/specializations")
    Call<Talents> getSpecs(@Path("charactername") String character,
                           @Path("realm") String realm,
                           @Query("namespace") String namespace,
                           @Query("locale") String locale,
                           @Query("access_token") String accessToken);


    @GET("profile/wow/character/{realm}/{charactername}")
    Call<CharacterSummary> getCharacter(@Path("charactername") String character,
                                        @Path("realm") String realm,
                                        @Query("namespace") String namespace,
                                        @Query("locale") String locale,
                                        @Query("access_token") String accessToken);

    @GET("profile/user/wow")
    Call<Account> getAccount(@Query("namespace") String namespace,
                             @Query("locale") String locale,
                             @Query("access_token") String accessToken);

    @GET("profile/wow/character/{realm}/{charactername}/pvp-summary")
    Call<PvPSummary> getPvPSummary(@Path("charactername") String character,
                                   @Path("realm") String realm,
                                   @Query("namespace") String namespace,
                                   @Query("locale") String locale,
                                   @Query("access_token") String accessToken);

    @GET("profile/wow/character/{realm}/{charactername}/pvp-bracket/{BRACKET}")
    Call<BracketStatistics> getPvPBrackets(@Path("charactername") String character,
                                           @Path("realm") String realm,
                                           @Path("BRACKET") String bracket,
                                           @Query("namespace") String namespace,
                                           @Query("locale") String locale,
                                           @Query("access_token") String accessToken);

    @GET
    Call<Tier> getDynamicTier(@Url String url,
                              @Query("locale") String locale,
                              @Query("access_token") String accessToken);

    @GET("profile/wow/character/{realm}/{charactername}/reputations")
    Call<Reputation> getReputations(@Path("charactername") String character,
                                    @Path("realm") String realm,
                                    @Query("namespace") String namespace,
                                    @Query("locale") String locale,
                                    @Query("access_token") String accessToken);

    //D3 Endpoints
    @GET("d3/profile/{battletag}/")
    Call<AccountInformation> getD3Profile(@Path("battletag") String battletag,
                                          @Query("namespace") String namespace,
                                          @Query("locale") String locale,
                                          @Query("access_token") String accessToken);

    @GET("d3/profile/{battletag}/hero/{id}")
    Call<CharacterInformation> getD3Hero(@Path("battletag") String battletag,
                                         @Path("id") long id,
                                         @Query("namespace") String namespace,
                                         @Query("locale") String locale,
                                         @Query("access_token") String accessToken);

    @GET("d3/profile/{battletag}/hero/{id}/items")
    Call<Items> getHeroItems(@Path("battletag") String battletag,
                             @Path("id") long id,
                             @Query("namespace") String namespace,
                             @Query("locale") String locale,
                             @Query("access_token") String accessToken);

    @GET("d3/data/{item}")
    Call<SingleItem> getItem(@Path("item") String item,
                             @Query("namespace") String namespace,
                             @Query("locale") String locale,
                             @Query("access_token") String accessToken);

    //Sc2 endpoints
    @GET("sc2/player/{id}")
    Call<List<Player>> getSc2Player(@Path("id") String id,
                                    @Query("namespace") String namespace,
                                    @Query("locale") String locale,
                                    @Query("access_token") String accessToken);

    @GET("sc2/profile/{region_id}/{realm_id}/{profile_id}")
    Call<Profile> getSc2Profile(@Path("region_id") int regionId,
                                @Path("realm_id") int realmId,
                                @Path("profile_id") String profileId,
                                @Query("namespace") String namespace,
                                @Query("locale") String locale,
                                @Query("access_token") String accessToken);

    //Overwatch endpoint
    @GET
    Call<com.BlizzardArmory.overwatch.Profile> getOWProfile(@Url String url);

}
