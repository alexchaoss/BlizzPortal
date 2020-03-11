package com.BlizzardArmory.warcraft.media

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Media {
    @SerializedName("_links")
    @Expose
    var links: Links? = null

    @SerializedName("character")
    @Expose
    var character: Character? = null

    @SerializedName("avatar_url")
    @Expose
    var avatarUrl: String? = null

    @SerializedName("bust_url")
    @Expose
    var bustUrl: String? = null

    @SerializedName("render_url")
    @Expose
    var renderUrl: String? = null

}