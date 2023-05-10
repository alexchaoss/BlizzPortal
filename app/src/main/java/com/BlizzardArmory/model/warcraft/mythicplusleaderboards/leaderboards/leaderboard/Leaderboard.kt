package com.BlizzardArmory.model.warcraft.mythicplusleaderboards.leaderboards.leaderboard

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Links
import com.google.gson.annotations.SerializedName

@Keep
data class Leaderboard(

    @SerializedName("_links") val _links: Links,
    @SerializedName("map") val map: GameMap,
    @SerializedName("period") val period: Int,
    @SerializedName("period_start_timestamp") val period_start_timestamp: Long,
    @SerializedName("period_end_timestamp") val period_end_timestamp: Long,
    @SerializedName("connected_realm") val connected_realm: ConnectedRealm,
    @SerializedName("leading_groups") val leading_groups: List<LeadingGroups>?,
    @SerializedName("keystone_affixes") val keystone_affixesstone_affixes: List<KeystoneAffixes>,
    @SerializedName("map_challenge_mode_id") val map_challenge_mode_id: Int,
    @SerializedName("name") val name: String
)