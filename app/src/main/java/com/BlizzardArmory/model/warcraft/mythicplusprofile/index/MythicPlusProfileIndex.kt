package com.BlizzardArmory.model.warcraft.mythicplusprofile.index

import com.BlizzardArmory.model.common.Links
import com.BlizzardArmory.model.warcraft.mythicplusprofile.Character
import com.google.gson.annotations.SerializedName


data class MythicPlusProfileIndex(
    @SerializedName("_links") var Links: Links,
    @SerializedName("current_period") var currentPeriod: CurrentPeriod,
    @SerializedName("seasons") var seasons: ArrayList<Seasons>,
    @SerializedName("character") var character: Character,
    @SerializedName("current_mythic_rating") var currentMythicRating: CurrentMythicRating
)