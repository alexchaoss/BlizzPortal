package com.BlizzardArmory.model.warcraft.guild.achievements

import com.google.gson.annotations.SerializedName


data class ChildCriteria(

    @SerializedName("id") val id: Long,
    @SerializedName("amount") val amount: Int,
    @SerializedName("is_completed") val is_completed: Boolean
)