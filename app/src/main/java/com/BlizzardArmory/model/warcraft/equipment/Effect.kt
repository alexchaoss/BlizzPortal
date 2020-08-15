package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Effect.
 */
data class Effect(

        @SerializedName("display_string")
        @Expose
        var displayString: String,

        @SerializedName("required_count")
        @Expose
        var requiredCount: Int

)