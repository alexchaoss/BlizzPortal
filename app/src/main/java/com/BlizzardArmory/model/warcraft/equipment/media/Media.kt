package com.BlizzardArmory.model.warcraft.equipment.media

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Media.
 */
data class Media(

        @SerializedName("_links")
        @Expose
        var links: Links,

        @SerializedName("assets")
        @Expose
        var assets: List<Asset>

)