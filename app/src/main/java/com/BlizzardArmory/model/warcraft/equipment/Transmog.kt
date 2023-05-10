package com.BlizzardArmory.model.warcraft.equipment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Transmog.
 */
@Keep
data class Transmog(

    @SerializedName("item")
    var item: Item,

    @SerializedName("display_string")
    var displayString: String,

    @SerializedName("item_modified_appearance_id")
    var itemModifiedAppearanceId: Long

)