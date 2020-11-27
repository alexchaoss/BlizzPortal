package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Essence.
 */
data class Essence(

        @SerializedName("key")
        var key: Key,

        @SerializedName("name")
        var name: String,

        @SerializedName("id")
        var id: Long

)