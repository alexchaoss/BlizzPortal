package com.BlizzardArmory.model.diablo.character.follower

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Followers.
 */
data class Followers(

        @SerializedName("templar")
        @Expose
        var templar: Templar,

        @SerializedName("scoundrel")
        @Expose
        var scoundrel: Scoundrel,

        @SerializedName("enchantress")
        @Expose
        var enchantress: Enchantress

)