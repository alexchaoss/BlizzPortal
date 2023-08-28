package com.BlizzardArmory.model.diablo.diablo3.character.follower

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Followers.
 */
@Keep
data class Followers(

    @SerializedName("templar")
    var templar: Templar,

    @SerializedName("scoundrel")
    var scoundrel: Scoundrel,

    @SerializedName("enchantress")
    var enchantress: Enchantress

)