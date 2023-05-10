package com.BlizzardArmory.model.warcraft.achievements.characterachievements

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class ChildCriteria(
    @SerializedName("id")
    val id: Long,
    @SerializedName("is_completed")
    val completed: Boolean
)