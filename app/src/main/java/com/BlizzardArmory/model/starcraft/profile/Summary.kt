package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.SerializedName


/**
 * The type Summary.
 */
data class Summary(

    @SerializedName("id")
    var id: String,

    @SerializedName("realm")
    var realm: Int,

    @SerializedName("displayName")
    var displayName: String,

    @SerializedName("clanName")
    var clanName: String,

    @SerializedName("clanTag")
    var clanTag: String,

    @SerializedName("portrait")
    var portrait: String,

    @SerializedName("decalTerran")
    var decalTerran: String,

    @SerializedName("decalProtoss")
    var decalProtoss: String,

    @SerializedName("decalZerg")
    var decalZerg: String,

    @SerializedName("totalSwarmLevel")
    var totalSwarmLevel: Int,

    @SerializedName("totalAchievementPoints")
    var totalAchievementPoints: Int

)