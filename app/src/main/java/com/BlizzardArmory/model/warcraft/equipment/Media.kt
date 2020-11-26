package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Media.
 */
data class Media(

        @SerializedName("key")
        var key: Key,

        @SerializedName("id")
        var id: Long

)