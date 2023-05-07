package com.BlizzardArmory.model.warcraft.mythicplusprofile

import com.google.gson.annotations.SerializedName


data class BestRuns(
    @SerializedName("completed_timestamp") var completedTimestamp: Long,
    @SerializedName("duration") var duration: Long,
    @SerializedName("keystone_level") var keystoneLevel: Int,
    @SerializedName("keystone_affixes") var keystoneAffixes: ArrayList<KeystoneAffixes> = arrayListOf(),
    @SerializedName("members") var members: ArrayList<Members> = arrayListOf(),
    @SerializedName("dungeon") var dungeon: Dungeon,
    @SerializedName("is_completed_within_time") var isCompletedWithinTime: Boolean,
    @SerializedName("mythic_rating") var mythicRating: MythicRating,
    @SerializedName("map_rating") var mapRating: MapRating
)