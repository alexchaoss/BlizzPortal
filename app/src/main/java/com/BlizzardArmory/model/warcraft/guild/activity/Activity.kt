package com.BlizzardArmory.model.warcraft.guild.activity

import com.google.gson.annotations.SerializedName


data class Activity(

    @SerializedName("type") val type: String
)