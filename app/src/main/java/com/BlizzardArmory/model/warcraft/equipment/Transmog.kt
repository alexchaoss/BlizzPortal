package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Transmog.
 */
data class Transmog(

        @SerializedName("item")
        @Expose
        var item: Item,

        @SerializedName("display_string")
        @Expose
        var displayString: String,

        @SerializedName("item_modified_appearance_id")
        @Expose
        var itemModifiedAppearanceId: Long

)