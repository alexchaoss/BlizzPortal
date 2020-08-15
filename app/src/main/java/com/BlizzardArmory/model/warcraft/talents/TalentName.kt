package com.BlizzardArmory.model.warcraft.talents

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Talent name.
 */
data class TalentName(

        @SerializedName("key")
        @Expose
        var key: Key,

        @SerializedName("name")
        @Expose
        var name: String,

        @SerializedName("id")
        @Expose
        var id: Long

)