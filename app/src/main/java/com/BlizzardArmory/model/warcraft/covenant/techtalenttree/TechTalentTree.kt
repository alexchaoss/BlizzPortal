package com.BlizzardArmory.model.warcraft.covenant.techtalenttree

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Links
import com.google.gson.annotations.SerializedName


@Keep
data class TechTalentTree(

    @SerializedName("_links") val links: Links,
    @SerializedName("id") val id: Long,
    @SerializedName("max_tiers") val max_tiers: Int,
    @SerializedName("talents") val talents: List<Talents>
)