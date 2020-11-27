package com.BlizzardArmory.model.warcraft.talents

import com.google.gson.annotations.SerializedName


/**
 * The type Pvp talent slot.
 */
data class PvpTalentSlot(

        @SerializedName("selected")
        var selected: Selected,

        @SerializedName("slot_number")
        var slotNumber: Int

)