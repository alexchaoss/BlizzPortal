package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Azerite details.
 */
data class AzeriteDetails(

        @SerializedName("selected_powers")
        @Expose
        var selectedPowers: List<SelectedPower>,

        @SerializedName("selected_powers_string")
        @Expose
        var selectedPowersString: String,

        @SerializedName("percentage_to_next_level")
        @Expose
        var percentageToNextLevel: Float,

        @SerializedName("selected_essences")
        @Expose
        var selectedEssences: List<SelectedEssence>,

        @SerializedName("level")
        @Expose
        var level: Level

)