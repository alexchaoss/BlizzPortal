package com.BlizzardArmory.model.warcraft.account

import com.google.gson.annotations.SerializedName


/**
 * The type Wow account.
 */
data class WowAccount(

        @SerializedName("id")
        var id: Long,

        @SerializedName("characters")
        var characters: List<Character>

)