package com.BlizzardArmory.model.warcraft.guild.achievements

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class CategoryProgress(

    @SerializedName("category") val category: Category,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("points") val points: Int
)