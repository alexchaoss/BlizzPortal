package com.BlizzardArmory.model.common

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Character.
 */
@Keep
data class Character(

    @SerializedName("key")
    var key: Key,

    @SerializedName("name")
    var name: String,

    @SerializedName("id")
    var id: Long,

    @SerializedName("realm")
    var realm: Realm

)