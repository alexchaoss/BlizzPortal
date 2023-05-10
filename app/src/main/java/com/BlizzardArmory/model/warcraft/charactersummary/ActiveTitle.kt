package com.BlizzardArmory.model.warcraft.charactersummary

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName


/**
 * The type Active title.
 */
@Keep
data class ActiveTitle(

    @SerializedName("key")
    var key: Key,

    @SerializedName("name")
    var name: String,

    @SerializedName("id")
    var id: Long,

    @SerializedName("display_string")
    var displayString: String

)