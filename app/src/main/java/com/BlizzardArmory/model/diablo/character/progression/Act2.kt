package com.BlizzardArmory.model.diablo.character.progression

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Act 2.
 */
data class Act2(

        @SerializedName("completed")
        @Expose
        var completed: Boolean,

        @SerializedName("completedQuests")
        @Expose
        var completedQuests: List<Any>
)