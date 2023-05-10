package com.BlizzardArmory.model.warcraft.equipment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Azerite details.
 */
@Keep
data class AzeriteDetails(

    @SerializedName("selected_powers")
    var selectedPowers: List<SelectedPower>,

    @SerializedName("selected_powers_string")
    var selectedPowersString: String,

    @SerializedName("percentage_to_next_level")
    var percentageToNextLevel: Float,

    @SerializedName("selected_essences")
    var selectedEssences: List<SelectedEssence>,

    @SerializedName("level")
    var level: Level

)