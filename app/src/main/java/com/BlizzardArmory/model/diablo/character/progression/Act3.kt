package com.BlizzardArmory.model.diablo.character.progression

import com.google.gson.annotations.SerializedName


/**
 * The type Act 3.
 */
data class Act3(

        @SerializedName("completed")
        var completed: Boolean,

        @SerializedName("completedQuests")
        var completedQuests: List<Any>
)