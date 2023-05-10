package com.BlizzardArmory.model.warcraft.guild.achievements

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.math.BigInteger


@Keep
data class ChildCriteria(

    @SerializedName("id") val id: Long,
    @SerializedName("amount") val amount: BigInteger,
    @SerializedName("is_completed") val is_completed: Boolean
)