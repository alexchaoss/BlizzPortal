package com.BlizzardArmory.model.starcraft.league

import com.google.gson.annotations.SerializedName


data class Tier(

    @SerializedName("id") val id: Int,
    @SerializedName("division") val division: List<Division>
)