package com.BlizzardArmory.model.warcraft.guild

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Achievements(

    @SerializedName("href") val href: String
)