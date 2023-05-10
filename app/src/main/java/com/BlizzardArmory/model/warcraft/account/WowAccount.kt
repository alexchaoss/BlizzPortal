package com.BlizzardArmory.model.warcraft.account

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Wow account.
 */
@Keep
data class WowAccount(

    @SerializedName("id")
    var id: Long,

    @SerializedName("characters")
    var characters: List<Character>

)