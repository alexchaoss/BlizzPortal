package com.BlizzardArmory.model.warcraft.charactersummary

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Appearance.
 */
@Keep
data class Appearance(

    @SerializedName("href")
    var href: String

)