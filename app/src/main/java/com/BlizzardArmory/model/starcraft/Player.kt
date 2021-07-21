package com.BlizzardArmory.model.starcraft

import com.google.gson.annotations.SerializedName


/**
 * The type Player.
 */
data class Player(

    @SerializedName("name")
    var name: String,

    @SerializedName("profileUrl")
    var profileUrl: String,

    @SerializedName("avatarUrl")
    var avatarUrl: String,

    @SerializedName("profileId")
    var profileId: String,

    @SerializedName("regionId")
    var regionId: Int,

    @SerializedName("realmId")
    var realmId: Int

)