package com.BlizzardArmory.model.diablo.diablo3.character.follower

import androidx.annotation.Keep
import com.BlizzardArmory.model.diablo.diablo3.character.ItemsCharacter
import com.BlizzardArmory.model.diablo.diablo3.character.Stats
import com.google.gson.annotations.SerializedName


/**
 * The type Templar.
 */
@Keep
data class Templar(

    @SerializedName("slug")
    var slug: String,

    @SerializedName("level")
    var level: Int,

    @SerializedName("itemsCharacter")
    var itemsCharacter: ItemsCharacter,

    @SerializedName("stats")
    var stats: Stats,

    @SerializedName("skills")
    var skills: List<Any>

)