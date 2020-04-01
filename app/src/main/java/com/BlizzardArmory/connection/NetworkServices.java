package com.BlizzardArmory.connection;

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

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface NetworkServices {

    //WoW Endpoints
    @GET("/profile/wow/character/{realm}/{charactername}/character-media")
    Call<Media> getMedia(@Path("charactername") String character,
                         @Path("realm") String realm,
                         @Query("namespace") String namespace,
                         @Query("locale") String locale,
                         @Query("access_token") String accessToken);

    @GET
    Call<Media> getDynamicMedia(@Url String url,
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
    @GET("d3/profile/btag/")
    Call<Talents> getD3Profile(@Query("namespace") String namespace,
                               @Query("locale") String locale,
                               @Query("access_token") String accessToken);

}
