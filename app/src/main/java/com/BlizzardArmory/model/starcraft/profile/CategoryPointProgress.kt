package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.SerializedName


/**
 * The type Category point progress.
 */
data class CategoryPointProgress(

        @SerializedName("categoryId")
        var categoryId: String,

        @SerializedName("pointsEarned")
        var pointsEarned: Int

)