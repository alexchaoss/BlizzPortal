package com.BlizzardArmory.model.warcraft.charactersummary

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Key
import com.BlizzardArmory.model.warcraft.common.Realm
import com.google.gson.annotations.SerializedName


/**
 * The type Guild.
 */
@Keep
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