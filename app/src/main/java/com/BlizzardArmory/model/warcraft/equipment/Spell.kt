package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Spell.
 */
data class Spell(

        @SerializedName("key")
        var key: Key,

        @SerializedName("name")
        var name: String,

        @SerializedName("id")
        var id: Long

)