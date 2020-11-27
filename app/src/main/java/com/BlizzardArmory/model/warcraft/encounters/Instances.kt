package com.BlizzardArmory.model.warcraft.encounters

import com.google.gson.annotations.SerializedName


data class Instances(

        @SerializedName("instance") val instance: Instance,
        @SerializedName("modes") val modes: List<Modes>
)