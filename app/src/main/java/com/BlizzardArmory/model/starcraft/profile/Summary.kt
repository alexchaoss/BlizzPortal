package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Summary.
 */
data class Summary(

        @SerializedName("id")
        @Expose
        var id: String,

        @SerializedName("realm")
        @Expose
        var realm: Int,

        @SerializedName("displayName")
        @Expose
        var displayName: String,

        @SerializedName("clanName")
        @Expose
        var clanName: String,

        @SerializedName("clanTag")
        @Expose
        var clanTag: String,

        @SerializedName("portrait")
        @Expose
        var portrait: String,

        @SerializedName("decalTerran")
        @Expose
        var decalTerran: String,

        @SerializedName("decalProtoss")
        @Expose
        var decalProtoss: String,

        @SerializedName("decalZerg")
        @Expose
        var decalZerg: String,

        @SerializedName("totalSwarmLevel")
        @Expose
        var totalSwarmLevel: Int,

        @SerializedName("totalAchievementPoints")
        @Expose
        var totalAchievementPoints: Int

)