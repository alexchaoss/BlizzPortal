package com.BlizzardArmory.model.warcraft.guild.media

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Links
import com.google.gson.annotations.SerializedName


/**
 * The type Media.
 */
@Keep
data class Media(

    @SerializedName("_links")
    var links: Links,

    @SerializedName("assets")
    var assets: List<Asset>,

    @SerializedName("id")
    var id: Long

)