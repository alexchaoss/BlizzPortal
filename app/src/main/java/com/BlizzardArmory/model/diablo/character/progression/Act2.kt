package com.BlizzardArmory.model.diablo.character.progression

import com.google.gson.annotations.SerializedName


/**
 * The type Act 2.
 */
data class Act2(

        @SerializedName("completed")
        var completed: Boolean,

        @SerializedName("completedQuests")
        var completedQuests: List<Any>
)