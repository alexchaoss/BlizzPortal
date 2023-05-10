package com.BlizzardArmory.model.starcraft

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Player.
 */
@Keep
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