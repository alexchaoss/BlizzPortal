package com.BlizzardArmory.model.warcraft.equipment.media

import com.google.gson.annotations.SerializedName


/**
 * The type Media.
 */
data class Media(

        @SerializedName("_links")
        var links: Links,

        @SerializedName("assets")
        var assets: List<Asset>

)