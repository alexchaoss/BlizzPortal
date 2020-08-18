package com.BlizzardArmory.model.warcraft.achievements

import com.google.gson.annotations.SerializedName

class DetailedAchievements : ArrayList<DetailedAchievement>() {
    @SerializedName("timestamp")
    var timestamp: Long? = null
}