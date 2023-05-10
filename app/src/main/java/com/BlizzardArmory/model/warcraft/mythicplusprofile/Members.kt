package com.BlizzardArmory.model.warcraft.mythicplusprofile

import androidx.annotation.Keep
import com.BlizzardArmory.model.warcraft.mythicplusprofile.season.Race
import com.google.gson.annotations.SerializedName


@Keep
data class Members(

    @SerializedName("character") var character: Character? = Character(),
    @SerializedName("specialization") var specialization: Specialization? = Specialization(),
    @SerializedName("race") var race: Race? = Race(),
    @SerializedName("equipped_item_level") var equippedItemLevel: Int? = null

)