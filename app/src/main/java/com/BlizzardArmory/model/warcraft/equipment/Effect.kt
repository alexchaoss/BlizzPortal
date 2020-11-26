package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Effect.
 */
data class Effect(

        @SerializedName("display_string")
        var displayString: String,

        @SerializedName("required_count")
        var requiredCount: Int

)