package com.BlizzardArmory.model.warcraft.charactersummary

import com.google.gson.annotations.SerializedName


/**
 * The type Gender.
 */
data class Gender(

        @SerializedName("type")
        var type: String,

        @SerializedName("name")
        var name: String

)