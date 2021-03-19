package com.BlizzardArmory.model.warcraft.guild

import com.BlizzardArmory.model.common.Faction
import com.BlizzardArmory.model.common.Links
import com.BlizzardArmory.model.common.Realm
import com.google.gson.annotations.SerializedName


data class Guild(

    @SerializedName("_links") val links: Links,
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("faction") val faction: Faction,
    @SerializedName("achievement_points") val achievement_points: Int,
    @SerializedName("member_count") val member_count: Int,
    @SerializedName("realm") val realm: Realm,
    @SerializedName("crest") val crest: Crest,
    @SerializedName("roster") val roster: Roster,
    @SerializedName("achievements") val achievements: Achievements,
    @SerializedName("created_timestamp") val created_timestamp: Long,
    @SerializedName("activity") val activity: Activity
)