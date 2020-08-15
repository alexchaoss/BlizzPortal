package com.BlizzardArmory.model.warcraft.talents

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Talents.
 */
data class Talents(

        @SerializedName("_links")
        @Expose
        var links: Links,

        @SerializedName("specializations")
        @Expose
        var specializations: List<Specialization>,

        @SerializedName("active_specialization")
        @Expose
        var activeSpecialization: ActiveSpecialization,

        @SerializedName("character")
        @Expose
        var character: Character

)