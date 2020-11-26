package com.BlizzardArmory.model.warcraft.media

import com.google.gson.annotations.SerializedName


class Character {
    @SerializedName("key")
        var key: Key? = null

    @SerializedName("name")
        var name: String? = null

    @SerializedName("id")
        var id: Long = 0

    @SerializedName("realm")
        var realm: Realm? = null

}