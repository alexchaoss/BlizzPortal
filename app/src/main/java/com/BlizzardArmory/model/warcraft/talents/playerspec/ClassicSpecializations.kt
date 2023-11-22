package com.BlizzardArmory.model.warcraft.talents.playerspec

import com.google.gson.annotations.SerializedName


data class ClassicSpecializations(
    @SerializedName("talents") var talents: ArrayList<Talents>,
    @SerializedName("specialization_name") var specializationName: String,
    @SerializedName("spent_points") var spentPoints: Int
)