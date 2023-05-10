package com.BlizzardArmory.model.starcraft.profile

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Category point progress.
 */
@Keep
data class CategoryPointProgress(

    @SerializedName("categoryId")
    var categoryId: String,

    @SerializedName("pointsEarned")
    var pointsEarned: Int

)