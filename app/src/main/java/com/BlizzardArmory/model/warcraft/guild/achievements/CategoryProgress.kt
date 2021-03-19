package com.BlizzardArmory.model.warcraft.guild.achievements

import com.google.gson.annotations.SerializedName


data class CategoryProgress(

    @SerializedName("category") val category: Category,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("points") val points: Int
)