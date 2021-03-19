package com.BlizzardArmory.model.warcraft.guild.activity

import com.google.gson.annotations.SerializedName


data class Mode(

    @SerializedName("type") val type: String,
    @SerializedName("name") val name: String
)