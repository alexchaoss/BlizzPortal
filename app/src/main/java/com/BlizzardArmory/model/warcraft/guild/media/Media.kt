package com.BlizzardArmory.model.warcraft.guild.media

import com.BlizzardArmory.model.common.Links
import com.google.gson.annotations.SerializedName


/**
 * The type Media.
 */
data class Media(

    @SerializedName("_links")
    var links: Links,

    @SerializedName("assets")
    var assets: List<Asset>,

    @SerializedName("id")
    var id: Long

)