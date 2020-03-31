package com.BlizzardArmory.warcraft.media

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Character {
    @SerializedName("key")
    @Expose
    var key: Key? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("id")
    @Expose
    var id: Long = 0

    @SerializedName("realm")
    @Expose
    var realm: Realm? = null

}