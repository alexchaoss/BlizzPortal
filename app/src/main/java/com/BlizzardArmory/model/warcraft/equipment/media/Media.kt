package com.BlizzardArmory.model.warcraft.equipment.media

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
    var assets: List<Asset>

)