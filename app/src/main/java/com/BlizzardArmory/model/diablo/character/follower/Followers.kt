package com.BlizzardArmory.model.diablo.character.follower

import com.google.gson.annotations.SerializedName


/**
 * The type Followers.
 */
data class Followers(

        @SerializedName("templar")
        var templar: Templar,

        @SerializedName("scoundrel")
        var scoundrel: Scoundrel,

        @SerializedName("enchantress")
        var enchantress: Enchantress

)