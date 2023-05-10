package com.BlizzardArmory.model.warcraft.achievements.characterachievements

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Statistics(

    @SerializedName("href") val href: String
)