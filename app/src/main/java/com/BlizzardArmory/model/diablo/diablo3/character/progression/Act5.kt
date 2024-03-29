package com.BlizzardArmory.model.diablo.diablo3.character.progression

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Act 5.
 */
@Keep
data class Act5(

    @SerializedName("completed")
    var completed: Boolean,

    @SerializedName("completedQuests")
    var completedQuests: List<Any>
)