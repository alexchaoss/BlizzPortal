package com.BlizzardArmory.model.warcraft.guild.achievements

import com.google.gson.annotations.SerializedName


data class Criteria(

    @SerializedName("id") val id: Long,
    @SerializedName("is_completed") val is_completed: Boolean,
    @SerializedName("child_criteria") val child_criteria: List<ChildCriteria>
)