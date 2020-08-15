package com.BlizzardArmory.model.diablo.character.follower

import com.BlizzardArmory.model.diablo.character.ItemsCharacter
import com.BlizzardArmory.model.diablo.character.Stats
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Enchantress.
 */
data class Enchantress(

        @SerializedName("slug")
        @Expose
        var slug: String,

        @SerializedName("level")
        @Expose
        var level: Int,

        @SerializedName("itemsCharacter")
        @Expose
        var itemsCharacter: ItemsCharacter,

        @SerializedName("stats")
        @Expose
        var stats: Stats,

        @SerializedName("skills")
        @Expose
        var skills: List<Any>

)