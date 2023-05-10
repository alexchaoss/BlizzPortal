package com.BlizzardArmory.model.warcraft.reputations.custom

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
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
