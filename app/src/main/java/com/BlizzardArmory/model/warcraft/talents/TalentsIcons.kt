package com.BlizzardArmory.model.warcraft.talents

import com.google.gson.annotations.SerializedName

data class TalentsIcons(

        @SerializedName("id")
        val id: Long,

        @SerializedName("description")
        val description: String,

        @SerializedName("column_index")
        val columnIndex: Long,

        @SerializedName("tier_index")
        val tierIndex: Long,

        @SerializedName("level")
        val level: Int,

        @SerializedName("playable_class_id")
        val playableClassId: Long,

        @SerializedName("icon")
        val icon: String,
)