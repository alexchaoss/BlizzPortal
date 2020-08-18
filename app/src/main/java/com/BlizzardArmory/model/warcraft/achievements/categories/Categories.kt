package com.BlizzardArmory.model.warcraft.achievements.categories

import com.google.gson.annotations.SerializedName


class Categories : ArrayList<Category>() {
    @SerializedName("timestamp")
    var timestamp: Long? = null
}