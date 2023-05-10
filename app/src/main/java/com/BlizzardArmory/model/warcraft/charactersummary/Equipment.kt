package com.BlizzardArmory.model.warcraft.charactersummary

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Equipment.
 */
@Keep
data class Equipment(

    @SerializedName("href")
    var href: String

)