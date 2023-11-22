package com.BlizzardArmory.model.warcraft.journal

import com.BlizzardArmory.model.common.Links
import com.google.gson.annotations.SerializedName


data class JournalExpansion(
    @SerializedName("_links") var Links: Links,
    @SerializedName("tiers") var tiers: ArrayList<Tiers>
)