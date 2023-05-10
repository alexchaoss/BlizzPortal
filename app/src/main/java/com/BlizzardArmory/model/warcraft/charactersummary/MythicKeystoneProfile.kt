package com.BlizzardArmory.model.warcraft.charactersummary

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Mythic keystone profile.
 */
@Keep
data class MythicKeystoneProfile(

    @SerializedName("href")
    var href: String

)