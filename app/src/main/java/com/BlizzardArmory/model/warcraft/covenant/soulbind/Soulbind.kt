package com.BlizzardArmory.model.warcraft.covenant.soulbind

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Links
import com.google.gson.annotations.SerializedName


@Keep
data class Soulbind(

    @SerializedName("_links") val links: Links,
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("covenant") val covenant: Covenant,
    @SerializedName("creature") val creature: Creature,
    @SerializedName("follower") val follower: Follower,
    @SerializedName("talent_tree") val techtalentTree: TechTalentTree
)