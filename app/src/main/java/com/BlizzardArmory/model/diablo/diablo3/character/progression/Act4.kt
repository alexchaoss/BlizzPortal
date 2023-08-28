package com.BlizzardArmory.model.diablo.diablo3.character.progression

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Act 4.
 */
@Keep
data class Act4(

    @SerializedName("completed")
    var completed: Boolean,

    @SerializedName("completedQuests")
    var completedQuests: List<Any>
)