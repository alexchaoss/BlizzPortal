package com.BlizzardArmory.model.warcraft.specialization

import com.google.gson.annotations.SerializedName

data class Specialization(

    @SerializedName("id") val id: Int,
    @SerializedName("playable_class_id") val playableClassId: Int,
    @SerializedName("role_id") val roleId: String
)