package com.BlizzardArmory.model.diablo.item

import com.BlizzardArmory.model.diablo.items.Type
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Single item.
 */
data class SingleItem(

        @SerializedName("accountBound")
        @Expose
        val accountBound: Boolean,

        @SerializedName("attributes")
        @Expose
        val attributes: Attributes,

        @SerializedName("color")
        @Expose
        val color: String,

        @SerializedName("damage")
        @Expose
        val damage: String,

        @SerializedName("damageHtml")
        @Expose
        val damageHtml: String,

        @SerializedName("p")
        @Expose
        val dps: String,

        @SerializedName("flavorText")
        @Expose
        val flavorText: String,

        @SerializedName("flavorTextHtml")
        @Expose
        val flavorTextHtml: String,

        @SerializedName("icon")
        @Expose
        val icon: String,

        @SerializedName("id")
        @Expose
        val id: String,

        @SerializedName("isSeasonRequiredToDrop")
        @Expose
        val isSeasonRequiredToDrop: Boolean,

        @SerializedName("name")
        @Expose
        val name: String,

        @SerializedName("randomAffixes")
        @Expose
        val randomAffixes: List<RandomAffix>,

        @SerializedName("requiredLevel")
        @Expose
        val requiredLevel: Long,

        @SerializedName("seasonRequiredToDrop")
        @Expose
        private val seasonRequiredToDrop: Long,

        @SerializedName("setItems")
        @Expose
        val setItems: List<Any>,

        @SerializedName("slots")
        @Expose
        val slots: List<String>,

        @SerializedName("slug")
        @Expose
        val slug: String,

        @SerializedName("stackSizeMax")
        @Expose
        val stackSizeMax: Long,

        @SerializedName("tooltipParams")
        @Expose
        val tooltipParams: String,


        @SerializedName("type")
        @Expose
        val type: Type,

        @SerializedName("typeName")
        @Expose
        val typeName: String

)