package com.BlizzardArmory.model.starcraft

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Player.
 */
data class Player(

        @SerializedName("name")
        @Expose
        var name: String,

        @SerializedName("profileUrl")
        @Expose
        var profileUrl: String,

        @SerializedName("avatarUrl")
        @Expose
        var avatarUrl: String,

        @SerializedName("profileId")
        @Expose
        var profileId: String,

        @SerializedName("regionId")
        @Expose
        var regionId: Int,

        @SerializedName("realmId")
        @Expose
        var realmId: Int

)