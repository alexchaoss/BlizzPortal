package com.BlizzardArmory.model.warcraft.achievements.characterachievements

import com.google.gson.annotations.SerializedName


data class ChildCriteria(
    @SerializedName("id")
    val id: Long,
    @SerializedName("is_completed")
    val completed: Boolean
)