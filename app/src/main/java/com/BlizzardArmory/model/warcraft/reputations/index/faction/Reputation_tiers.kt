package com.BlizzardArmory.model.warcraft.reputations.index.faction

import com.google.gson.annotations.SerializedName


data class Reputation_tiers(

        @SerializedName("key") val key: Key,
        @SerializedName("id") val id: Int
)