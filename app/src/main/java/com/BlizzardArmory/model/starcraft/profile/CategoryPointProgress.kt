package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Category point progress.
 */
data class CategoryPointProgress(

        @SerializedName("categoryId")
        @Expose
        var categoryId: String,

        @SerializedName("pointsEarned")
        @Expose
        var pointsEarned: Int

)