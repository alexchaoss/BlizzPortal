package com.BlizzardArmory.model.warcraft.guild

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Roster(

    @SerializedName("href") val href: String
)