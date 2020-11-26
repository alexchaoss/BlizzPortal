package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Transmog.
 */
data class Transmog(

        @SerializedName("item")
        var item: Item,

        @SerializedName("display_string")
        var displayString: String,

        @SerializedName("item_modified_appearance_id")
        var itemModifiedAppearanceId: Long

)