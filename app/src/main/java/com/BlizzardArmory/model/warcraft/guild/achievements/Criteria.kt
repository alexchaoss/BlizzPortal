package com.BlizzardArmory.model.warcraft.guild.achievements

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Criteria(

    @SerializedName("id") val id: Long,
    @SerializedName("is_completed") val is_completed: Boolean,
    @SerializedName("child_criteria") val child_criteria: List<ChildCriteria>
)