package com.BlizzardArmory.warcraft.media

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Realm {
    @SerializedName("key")
    @Expose
    var key: Key_? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("id")
    @Expose
    var id: Long = 0

    @SerializedName("slug")
    @Expose
    var slug: String? = null

}