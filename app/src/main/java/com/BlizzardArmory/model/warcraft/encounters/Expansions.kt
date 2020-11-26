package com.BlizzardArmory.model.warcraft.encounters

import com.google.gson.annotations.SerializedName


data class Expansions(

        @SerializedName("expansion") val expansion: Expansion,
        @SerializedName("instances") val instances: List<Instances>
)