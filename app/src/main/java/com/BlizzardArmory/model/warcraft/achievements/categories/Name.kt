package com.BlizzardArmory.model.warcraft.achievements.categories


import com.google.gson.annotations.SerializedName


data class Name(
    @SerializedName("name")
    val name: NameLocalized
)