package com.BlizzardArmory.model.warcraft.charactersummary

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Character summary.
 */
data class CharacterSummary(

        @SerializedName("_links")
        @Expose
        var links: Links,

        @SerializedName("id")
        @Expose
        var id: Long,

        @SerializedName("name")
        @Expose
        var name: String,

        @SerializedName("gender")
        @Expose
        var gender: Gender,

        @SerializedName("faction")
        @Expose
        var faction: Faction,

        @SerializedName("race")
        @Expose
        var race: Race,

        @SerializedName("character_class")
        @Expose
        var characterClass: CharacterClass,

        @SerializedName("active_spec")
        @Expose
        var activeSpec: ActiveSpec,

        @SerializedName("realm")
        @Expose
        var realm: Realm,

        @SerializedName("guild")
        @Expose
        var guild: Guild,

        @SerializedName("level")
        @Expose
        var level: Int,

        @SerializedName("experience")
        @Expose
        var experience: Int,

        @SerializedName("achievement_points")
        @Expose
        var achievementPoints: Int,

        @SerializedName("achievements")
        @Expose
        var achievements: Achievements,

        @SerializedName("titles")
        @Expose
        var titles: Titles,

        @SerializedName("pvp_summary")
        @Expose
        var pvpSummary: PvpSummary,

        @SerializedName("raid_progression")
        @Expose
        var raidProgression: RaidProgression,

        @SerializedName("media")
        @Expose
        var media: Media,

        @SerializedName("last_login_timestamp")
        @Expose
        var lastLoginTimestamp: Long,

        @SerializedName("average_item_level")
        @Expose
        var averageItemLevel: Int,

        @SerializedName("equipped_item_level")
        @Expose
        var equippedItemLevel: Int,

        @SerializedName("specializations")
        @Expose
        var specializations: Specializations,

        @SerializedName("statistics")
        @Expose
        var statistics: Statistics,

        @SerializedName("mythic_keystone_profile")
        @Expose
        var mythicKeystoneProfile: MythicKeystoneProfile,

        @SerializedName("equipment")
        @Expose
        var equipment: Equipment,

        @SerializedName("appearance")
        @Expose
        var appearance: Appearance,

        @SerializedName("collections")
        @Expose
        var collections: Collections,

        @SerializedName("active_title")
        @Expose
        var activeTitle: ActiveTitle,

        @SerializedName("reputations")
        @Expose
        var reputations: Reputations

)