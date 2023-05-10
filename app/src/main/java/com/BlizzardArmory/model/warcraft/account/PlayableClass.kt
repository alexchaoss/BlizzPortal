package com.BlizzardArmory.model.warcraft.account

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName


/**
 * The type Playable class.
 */
@Keep
data class PlayableClass(

    @SerializedName("key")
    var key: Key,

    @SerializedName("name")
    var name: String,

    @SerializedName("id")
    var id: Long

)