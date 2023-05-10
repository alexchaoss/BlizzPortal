package com.BlizzardArmory.model.warcraft.guild.media

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Asset.
 */
@Keep
data class Asset(

    @SerializedName("key")
    var key: String,

    @SerializedName("value")
    var value: String

)