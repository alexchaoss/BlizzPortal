package com.BlizzardArmory.model.warcraft.talents

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Pvp talent slot.
 */
data class PvpTalentSlot(

        @SerializedName("selected")
        @Expose
        var selected: Selected,

        @SerializedName("slot_number")
        @Expose
        var slotNumber: Int

)