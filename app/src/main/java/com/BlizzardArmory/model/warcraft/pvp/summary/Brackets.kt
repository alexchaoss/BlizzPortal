package com.BlizzardArmory.model.warcraft.pvp.summary

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Brackets(

    @SerializedName("href") val href: String
)