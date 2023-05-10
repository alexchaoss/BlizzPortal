package com.BlizzardArmory.model.warcraft.achievements.categories


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Name(
    @SerializedName("name")
    val name: NameLocalized
)