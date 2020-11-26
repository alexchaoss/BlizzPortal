package com.BlizzardArmory.model.warcraft.media

import com.google.gson.annotations.SerializedName


class Realm {
    @SerializedName("key")
        var key: Key_? = null

    @SerializedName("name")
        var name: String? = null

    @SerializedName("id")
        var id: Long = 0

    @SerializedName("slug")
        var slug: String? = null

}