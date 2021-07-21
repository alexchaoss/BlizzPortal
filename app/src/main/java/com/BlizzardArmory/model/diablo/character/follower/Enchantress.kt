package com.BlizzardArmory.model.diablo.character.follower

import com.BlizzardArmory.model.diablo.character.ItemsCharacter
import com.BlizzardArmory.model.diablo.character.Stats
import com.google.gson.annotations.SerializedName


/**
 * The type Enchantress.
 */
data class Enchantress(

    @SerializedName("slug") var slug: String,

    @SerializedName("level") var level: Int,

    @SerializedName("itemsCharacter")
    var itemsCharacter: ItemsCharacter,

    @SerializedName("stats")
    var stats: Stats,

    @SerializedName("skills")
    var skills: List<Any>

)