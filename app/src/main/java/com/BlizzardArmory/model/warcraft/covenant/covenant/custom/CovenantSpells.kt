package com.BlizzardArmory.model.warcraft.covenant.covenant.custom

import com.google.gson.annotations.SerializedName


data class CovenantSpells(

    @SerializedName("covenant_id") val covenant_id: Int,
    @SerializedName("playable_class_id") val playable_class_id: Int,
    @SerializedName("spell_tooltip_id") val spell_tooltip_id: Int,
    @SerializedName("icon") val icon: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("cast_time") val castTime: String,
    @SerializedName("cooldown") val cooldown: String,
    @SerializedName("range") val range: String,
    @SerializedName("power_cost") val powerCost: String?
)