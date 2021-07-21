package com.BlizzardArmory.model.diablo.character.progression

import com.google.gson.annotations.SerializedName


/**
 * The type Act 4.
 */
data class Act4(

    @SerializedName("completed")
    var completed: Boolean,

    @SerializedName("completedQuests")
    var completedQuests: List<Any>
)