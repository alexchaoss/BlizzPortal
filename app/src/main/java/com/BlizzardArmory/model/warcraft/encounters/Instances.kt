package com.BlizzardArmory.model.warcraft.encounters

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Instances(

    @SerializedName("instance") val instance: Instance,
    @SerializedName("modes") val modes: List<Modes>
)