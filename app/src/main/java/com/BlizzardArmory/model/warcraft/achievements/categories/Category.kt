package com.BlizzardArmory.model.warcraft.achievements.categories


import com.google.gson.annotations.SerializedName


data class Category(
        @SerializedName("alliance_points")
        val alliancePoints: Int,
        @SerializedName("alliance_quantity")
        val allianceQuantity: Int,
        @SerializedName("display_order")
        val displayOrder: Int,
        @SerializedName("horde_points")
        val hordePoints: Int,
        @SerializedName("horde_quantity")
        val hordeQuantity: Int,
        @SerializedName("id")
        val id: Long,
        @SerializedName("is_guild_category")
        val isGuildCategory: Boolean,
        @SerializedName("name")
        val name: String,
        @SerializedName("parent_category_id")
        val parentCategoryId: Long? = null
)