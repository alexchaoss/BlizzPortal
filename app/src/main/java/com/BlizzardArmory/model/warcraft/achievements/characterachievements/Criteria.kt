package com.BlizzardArmory.model.warcraft.achievements.characterachievements

import com.google.gson.annotations.SerializedName


data class Criteria(

    @SerializedName("id") val id: Int,
    @SerializedName("amount") val amount: Int,
    @SerializedName("is_completed") val is_completed: Boolean,
    @SerializedName("child_criteria") val childCriteria: ArrayList<ChildCriteria>
)