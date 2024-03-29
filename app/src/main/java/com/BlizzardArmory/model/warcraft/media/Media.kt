package com.BlizzardArmory.model.warcraft.media

import com.BlizzardArmory.model.common.Links
import com.BlizzardArmory.model.warcraft.common.Character
import com.google.gson.annotations.SerializedName


class Media {
    @SerializedName("_links")
    var links: Links? = null

    @SerializedName("character")
    var character: Character? = null

    @SerializedName("assets")
    var assets: List<Asset>? = null

}