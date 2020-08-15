package com.BlizzardArmory.model.warcraft.charactersummary

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Mythic keystone profile.
 */
data class MythicKeystoneProfile(

        @SerializedName("href")
        @Expose
        var href: String

)