package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Self.
 */
data class Self(

        @SerializedName("href")
        @Expose
        var href: String

)