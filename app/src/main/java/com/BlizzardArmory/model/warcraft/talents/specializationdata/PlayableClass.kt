package com.BlizzardArmory.model.warcraft.talents.specializationdata

import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName


/**
 * The type Playable class.
 */
data class PlayableClass(

    @SerializedName("key")
    var key: Key,

    @SerializedName("name")
    var name: String,

    @SerializedName("id")
    var id: Long

)