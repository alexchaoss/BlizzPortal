package com.BlizzardArmory.model.warcraft.reputations.custom

import com.google.gson.annotations.SerializedName

data class ReputationPlusParentInfo(
    @SerializedName("id")
    val id: Long,
    @SerializedName("parent_faction_id")
    val parentFactionId: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("is_header")
    val isHeader: Boolean
)
