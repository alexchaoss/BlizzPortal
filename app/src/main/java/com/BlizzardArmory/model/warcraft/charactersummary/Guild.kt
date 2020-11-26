package com.BlizzardArmory.model.warcraft.charactersummary

import com.google.gson.annotations.SerializedName


/**
 * The type Guild.
 */
data class Guild(

        @SerializedName("key")
        var key: Key,

        @SerializedName("name")
        var name: String,

        @SerializedName("id")
        var id: Long,

        @SerializedName("realm")
        var realm: Realm

)