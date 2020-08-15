package com.BlizzardArmory.model.warcraft.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Links.
 */
data class Links(

        @SerializedName("self")
        @Expose
        var self: Self,

        @SerializedName("user")
        @Expose
        var user: User,

        @SerializedName("profile")
        @Expose
        var profile: Profile

)