package com.BlizzardArmory.model.warcraft.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Wow account.
 */
data class WowAccount(

        @SerializedName("id")
        @Expose
        var id: Long,

        @SerializedName("characters")
        @Expose
        var characters: List<Character>

)