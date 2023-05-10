package com.BlizzardArmory.model.warcraft.charactersummary

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Titles.
 */
@Keep
data class Titles(

    @SerializedName("href")
    var href: String

)