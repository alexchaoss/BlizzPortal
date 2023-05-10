package com.BlizzardArmory.model.warcraft.charactersummary

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Media.
 */
@Keep
data class Media(

    @SerializedName("href")
    var href: String

)