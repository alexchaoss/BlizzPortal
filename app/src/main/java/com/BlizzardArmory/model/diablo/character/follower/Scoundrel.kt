package com.BlizzardArmory.model.diablo.character.follower

import androidx.annotation.Keep
import com.BlizzardArmory.model.diablo.character.ItemsCharacter
import com.BlizzardArmory.model.diablo.character.Stats
import com.google.gson.annotations.SerializedName


/**
 * The type Scoundrel.
 */
@Keep
data class Scoundrel(

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