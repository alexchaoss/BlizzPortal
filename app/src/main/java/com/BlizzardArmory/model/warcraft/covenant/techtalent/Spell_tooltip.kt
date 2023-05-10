package com.BlizzardArmory.model.warcraft.covenant.techtalent

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Spell_tooltip(

    @SerializedName("spell") val spell: Spell,
    @SerializedName("description") val description: String,
    @SerializedName("cast_time") val cast_time: String
)