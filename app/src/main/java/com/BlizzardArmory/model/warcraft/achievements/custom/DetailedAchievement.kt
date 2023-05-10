package com.BlizzardArmory.model.warcraft.achievements.custom

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class DetailedAchievement(

    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("points") val points: Int,
    @SerializedName("is_account_wide") val is_account_wide: Boolean,
    @SerializedName("display_order") val display_order: Int,
    @SerializedName("category_id") val category_id: Long,
    @SerializedName("category") val category: String,
    @SerializedName("parent_category_id") val parent_category_id: Long,
    @SerializedName("parent_category_name") val parent_category_name: String,
    @SerializedName("icon") val icon: String
)