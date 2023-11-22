package com.BlizzardArmory.network.services

import com.BlizzardArmory.model.warcraft.account.Account
import com.BlizzardArmory.model.warcraft.achievements.characterachievements.Achievements
import com.BlizzardArmory.model.warcraft.charactersummary.CharacterSummary
import com.BlizzardArmory.model.warcraft.covenant.character.soulbind.CharacterSoulbinds
import com.BlizzardArmory.model.warcraft.covenant.conduit.Conduit
import com.BlizzardArmory.model.warcraft.covenant.soulbind.Soulbind
import com.BlizzardArmory.model.warcraft.covenant.techtalent.TalentTree
import com.BlizzardArmory.model.warcraft.covenant.techtalent.TechTalent
import com.BlizzardArmory.model.warcraft.covenant.techtalenttree.TechTalentTree
import com.BlizzardArmory.model.warcraft.encounters.EncountersInformation
import com.BlizzardArmory.model.warcraft.equipment.Equipment
import com.BlizzardArmory.model.warcraft.guild.Guild
import com.BlizzardArmory.model.warcraft.guild.achievements.AchievementsInformation
import com.BlizzardArmory.model.warcraft.guild.activity.ActivitiesInformation
import com.BlizzardArmory.model.warcraft.guild.roster.Roster
import com.BlizzardArmory.model.warcraft.journal.JournalExpansion
import com.BlizzardArmory.model.warcraft.media.Media
import com.BlizzardArmory.model.warcraft.mythicplusleaderboards.leaderboards.index.LeaderboardsIndex
import com.BlizzardArmory.model.warcraft.mythicplusleaderboards.leaderboards.leaderboard.Leaderboard
import com.BlizzardArmory.model.warcraft.mythicplusleaderboards.season.Season
import com.BlizzardArmory.model.warcraft.mythicplusleaderboards.season.index.SeasonsIndex
import com.BlizzardArmory.model.warcraft.mythicplusprofile.index.MythicPlusProfileIndex
import com.BlizzardArmory.model.warcraft.mythicplusprofile.season.MythicPlusProfileSeason
import com.BlizzardArmory.model.warcraft.mythicraid.MythicRaidLeaderboard
import com.BlizzardArmory.model.warcraft.pvp.bracket.BracketStatistics
import com.BlizzardArmory.model.warcraft.pvp.leaderboards.season.SeasonIndex
import com.BlizzardArmory.model.warcraft.pvp.summary.PvPSummary
import com.BlizzardArmory.model.warcraft.pvp.tiers.Tier
import com.BlizzardArmory.model.warcraft.realm.connected.ConnectedRealms
import com.BlizzardArmory.model.warcraft.reputations.characterreputations.Reputation
import com.BlizzardArmory.model.warcraft.statistic.Statistic
import com.BlizzardArmory.model.warcraft.talents.playerspec.PlayerSpecializations
import com.BlizzardArmory.model.warcraft.talents.trees.TalentTrees
import com.BlizzardArmory.network.NetworkUtils
import retrofit2.Response
import retrofit2.http.*

interface WoWNetworkServices {
    //Game Data
    @POST("/data/wow/search/connected-realms")
    suspend fun getConnectedRealms(
        @Body query: com.BlizzardArmory.model.warcraft.realm.connected.Query,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale,
        @Query("classic") classic: Boolean? = null,
        @Query("classic1x") classic1x: Boolean? = null
    ): Response<ConnectedRealms>

    @GET
    suspend fun getDynamicEquipmentMedia(
        @Url url: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<com.BlizzardArmory.model.warcraft.equipment.media.Media>

    @GET
    suspend fun getDynamicTier(
        @Url url: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<Tier>

    @GET("/data/wow/talent-tree/index")
    suspend fun getTalentTrees(
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<TalentTrees>

    @GET("/data/wow/talent-tree/{treeId}/playable-specialization/{specId}")
    suspend fun getTalentTree(
        @Path("treeId") treeId: Long,
        @Path("specId") specId: Long,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<TalentTree>

    @GET("/data/wow/tech-talent-tree/{id}")
    suspend fun getTechTree(
        @Path("id") id: Long,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<TechTalentTree>

    @GET("/data/wow/tech-talent/{techTalentId}")
    suspend fun getTechTalent(
        @Path("techTalentId") techTalentId: Int,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<TechTalent>

    @GET("/data/wow/media/tech-talent/{techTalentId}")
    suspend fun getTechTalentMedia(
        @Path("techTalentId") techTalentId: Int,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<Media>

    @GET("/data/wow/covenant/conduit/{conduitId}")
    suspend fun getConduit(
        @Path("conduitId") conduitId: Int,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<Conduit>

    @GET("/data/wow/media/spell/{spellId}")
    suspend fun getSpellMedia(
        @Path("spellId") spellId: Int,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<Media>

    @GET("/data/wow/journal-expansion/index")
    suspend fun getJournalExpansions(
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<JournalExpansion>

    @GET("/data/wow/leaderboard/hall-of-fame/{raid}/{faction}")
    suspend fun getMythicRaidLeaderboards(
        @Path("raid") raid: String,
        @Path("faction") faction: String,
        @Query("namespace") namespace: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<MythicRaidLeaderboard>

    @GET("/data/wow/guild/{realmSlug}/{nameSlug}")
    suspend fun getGuildSummary(
        @Path("realmSlug") realmSlug: String,
        @Path("nameSlug") nameSlug: String,
        @Query("namespace") namespace: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale,
        @Query("classic") classic: Boolean? = NetworkUtils.classic,
        @Query("classic1x") classic1x: Boolean? = NetworkUtils.classic1x
    ): Response<Guild>

    @GET("/data/wow/guild/{realmSlug}/{nameSlug}/activity")
    suspend fun getGuildActivity(
        @Path("realmSlug") realmSlug: String,
        @Path("nameSlug") nameSlug: String,
        @Query("namespace") namespace: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale,
        @Query("classic") classic: Boolean? = NetworkUtils.classic,
        @Query("classic1x") classic1x: Boolean? = NetworkUtils.classic1x
    ): Response<ActivitiesInformation>

    @GET("/data/wow/guild/{realmSlug}/{nameSlug}/roster")
    suspend fun getGuildRoster(
        @Path("realmSlug") realmSlug: String,
        @Path("nameSlug") nameSlug: String,
        @Query("namespace") namespace: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale,
        @Query("classic") classic: Boolean? = NetworkUtils.classic,
        @Query("classic1x") classic1x: Boolean? = NetworkUtils.classic1x
    ): Response<Roster>

    @GET("/data/wow/guild/{realmSlug}/{nameSlug}/achievements")
    suspend fun getGuildAchievements(
        @Path("realmSlug") realmSlug: String,
        @Path("nameSlug") nameSlug: String,
        @Query("namespace") namespace: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale,
        @Query("classic") classic: Boolean? = NetworkUtils.classic,
        @Query("classic1x") classic1x: Boolean? = NetworkUtils.classic1x
    ): Response<AchievementsInformation>

    @GET("/data/wow/media/guild-crest/border/{id}")
    suspend fun getGuildCrestBorder(
        @Path("id") id: Int
    ): Response<com.BlizzardArmory.model.warcraft.guild.media.Media>

    @GET("/data/wow/media/guild-crest/emblem/{id}")
    suspend fun getGuildCrestEmblem(
        @Path("id") id: Int
    ): Response<com.BlizzardArmory.model.warcraft.guild.media.Media>

    @GET("/data/wow/mythic-keystone/season/index")
    suspend fun getMythicKeystoneSeasonsIndex(
        @Query("namespace") namespace: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<SeasonsIndex>

    @GET("/data/wow/mythic-keystone/season/{seasonId}")
    suspend fun getMythicKeystoneSeason(
        @Path("seasonId") seasonId: Int,
        @Query("namespace") namespace: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<Season>

    @GET("/data/wow/connected-realm/{connectedRealmId}/mythic-leaderboard/index")
    suspend fun getMythicKeystoneLeaderboardsIndex(
        @Path("connectedRealmId") connectedRealmId: Int,
        @Query("namespace") namespace: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<LeaderboardsIndex>

    @GET("/data/wow/connected-realm/{connectedRealmId}/mythic-leaderboard/{dungeonId}/period/{period}")
    suspend fun getMythicKeystoneLeaderboard(
        @Path("connectedRealmId") connectedRealmId: Int,
        @Path("dungeonId") dungeonId: Long,
        @Path("period") period: Int,
        @Query("namespace") namespace: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<Leaderboard>

    @GET("data/wow/media/keystone-affix/{id}")
    suspend fun getMythicKeystoneAffixMedia(
        @Path("id") id: Int,
        @Query("namespace") namespace: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<com.BlizzardArmory.model.warcraft.mythicplusleaderboards.affixes.Media>

    @GET("/data/wow/pvp-season/index")
    suspend fun getPvPSeasonIndex(
        @Query("namespace") namespace: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<SeasonIndex>

    @GET("/data/wow/pvp-season/{pvpSeasonId}/pvp-leaderboard/{pvpBracket}")
    suspend fun getPvPLeaderboard(
        @Path("pvpSeasonId") pvpSeasonId: Int,
        @Path("pvpBracket") pvpBracket: String,
        @Query("namespace") namespace: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<com.BlizzardArmory.model.warcraft.pvp.leaderboards.Leaderboard>

    @GET("/data/wow/covenant/soulbind/{soulbindId}")
    suspend fun getSoulbind(
        @Path("soulbindId") soulbindId: Long,
        @Query("namespace") namespace: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<Soulbind>

    //Profile

    @GET("/profile/wow/character/{realm}/{character}/character-media")
    suspend fun getMedia(
        @Path("character", encoded = true) character: String,
        @Path("realm", encoded = true) realm: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale,
        @Query("classic") classic: Boolean? = NetworkUtils.classic,
        @Query("classic1x") classic1x: Boolean? = NetworkUtils.classic1x
    ): Response<Media>

    @GET("/profile/wow/character/{realm}/{character}/achievements")
    suspend fun getCharacterAchievements(
        @Path("character", encoded = true) character: String,
        @Path("realm", encoded = true) realm: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale,
        @Query("classic") classic: Boolean? = NetworkUtils.classic,
        @Query("classic1x") classic1x: Boolean? = NetworkUtils.classic1x
    ): Response<Achievements>

    @GET("/profile/wow/character/{realm}/{character}/encounters/raids")
    suspend fun getEncounters(
        @Path("character", encoded = true) character: String,
        @Path("realm", encoded = true) realm: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale,
        @Query("classic") classic: Boolean? = NetworkUtils.classic,
        @Query("classic1x") classic1x: Boolean? = NetworkUtils.classic1x
    ): Response<EncountersInformation>

    @GET("/profile/wow/character/{realm}/{character}/equipment")
    suspend fun getEquippedItems(
        @Path("character", encoded = true) character: String,
        @Path("realm", encoded = true) realm: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale,
        @Query("classic") classic: Boolean? = NetworkUtils.classic,
        @Query("classic1x") classic1x: Boolean? = NetworkUtils.classic1x
    ): Response<Equipment>

    @GET("/profile/wow/character/{realm}/{character}/statistics")
    suspend fun getStats(
        @Path("character", encoded = true) character: String,
        @Path("realm", encoded = true) realm: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale,
        @Query("classic") classic: Boolean? = NetworkUtils.classic,
        @Query("classic1x") classic1x: Boolean? = NetworkUtils.classic1x
    ): Response<Statistic>

    @GET("/profile/wow/character/{realm}/{character}/specializations")
    suspend fun getSpecs(
        @Path("character", encoded = true) character: String,
        @Path("realm", encoded = true) realm: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale,
        @Query("classic") classic: Boolean? = NetworkUtils.classic,
        @Query("classic1x") classic1x: Boolean? = NetworkUtils.classic1x
    ): Response<PlayerSpecializations>


    @GET("/profile/wow/character/{realm}/{character}")
    suspend fun getCharacter(
        @Path("character", encoded = true) character: String,
        @Path("realm", encoded = true) realm: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale,
        @Query("classic") classic: Boolean? = NetworkUtils.classic,
        @Query("classic1x") classic1x: Boolean? = NetworkUtils.classic1x
    ): Response<CharacterSummary>

    @GET("/profile/user/wow")
    suspend fun getAccount(
        @Query("token") accessToken: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale,
        @Query("classic") classic: Boolean? = NetworkUtils.classic,
        @Query("classic1x") classic1x: Boolean? = NetworkUtils.classic1x
    ): Response<Account>

    @GET("/profile/wow/character/{realm}/{character}/pvp-summary")
    suspend fun getPvPSummary(
        @Path("character", encoded = true) character: String,
        @Path("realm", encoded = true) realm: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale,
        @Query("classic") classic: Boolean? = NetworkUtils.classic,
        @Query("classic1x") classic1x: Boolean? = NetworkUtils.classic1x
    ): Response<PvPSummary>

    @GET("/profile/wow/character/{realm}/{character}/pvp-bracket/{BRACKET}")
    suspend fun getPvPBrackets(
        @Path("character", encoded = true) character: String,
        @Path("realm", encoded = true) realm: String,
        @Path("BRACKET") bracket: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale,
        @Query("classic") classic: Boolean? = NetworkUtils.classic,
        @Query("classic1x") classic1x: Boolean? = NetworkUtils.classic1x
    ): Response<BracketStatistics>

    @GET("/profile/wow/character/{realm}/{character}/reputations")
    suspend fun getReputations(
        @Path("character", encoded = true) character: String,
        @Path("realm", encoded = true) realm: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale,
        @Query("classic") classic: Boolean? = NetworkUtils.classic,
        @Query("classic1x") classic1x: Boolean? = NetworkUtils.classic1x
    ): Response<Reputation>

    @GET("/profile/wow/character/{realm}/{character}/soulbinds")
    suspend fun getSoulbinds(
        @Path("character", encoded = true) character: String,
        @Path("realm", encoded = true) realm: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<CharacterSoulbinds>

    @GET("/profile/wow/character/{realm}/{character}/mythic-keystone-profile")
    suspend fun getMythicKeystoneProfileIndex(
        @Path("character", encoded = true) character: String,
        @Path("realm", encoded = true) realm: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<MythicPlusProfileIndex>

    @GET("/profile/wow/character/{realm}/{character}/mythic-keystone-profile/season/{seasonId}")
    suspend fun getMythicKeystoneProfileSeason(
        @Path("seasonId") seasonId: Int,
        @Path("character", encoded = true) character: String,
        @Path("realm", encoded = true) realm: String,
        @Query("region") region: String = NetworkUtils.region,
        @Query("locale") locale: String = NetworkUtils.locale
    ): Response<MythicPlusProfileSeason>
}