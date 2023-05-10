package com.BlizzardArmory.model.warcraft.encounters

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Expansions(

    @SerializedName("expansion") val expansion: Expansion,
    @SerializedName("instances") val instances: List<Instances>
)