package com.BlizzardArmory.model.warcraft.specialization

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Specialization(

    @SerializedName("id") val id: Int,
    @SerializedName("playable_class_id") val playableClassId: Int,
    @SerializedName("role_id") val roleId: String
)