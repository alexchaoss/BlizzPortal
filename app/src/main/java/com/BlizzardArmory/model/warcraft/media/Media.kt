package com.BlizzardArmory.model.warcraft.media

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Media {
    @SerializedName("_links")
    @Expose
    var links: Links? = null

    @SerializedName("character")
    @Expose
    var character: Character? = null

    @SerializedName("assets")
    @Expose
    var assets: List<Asset>? = null

}