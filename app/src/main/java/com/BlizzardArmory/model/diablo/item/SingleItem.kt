package com.BlizzardArmory.model.diablo.item

import androidx.annotation.Keep
import com.BlizzardArmory.model.diablo.items.Type
import com.google.gson.annotations.SerializedName


/**
 * The type Single item.
 */
@Keep
data class SingleItem(

    @SerializedName("accountBound")

    val accountBound: Boolean,

    @SerializedName("attributes")

    val attributes: Attributes,

    @SerializedName("color")

    val color: String,

    @SerializedName("damage")

    val damage: String,

    @SerializedName("damageHtml")

    val damageHtml: String,

    @SerializedName("p")

    val dps: String,

    @SerializedName("flavorText")

    val flavorText: String,

    @SerializedName("flavorTextHtml")

    val flavorTextHtml: String,

    @SerializedName("icon")

    val icon: String,

    @SerializedName("id")

    val id: String,

    @SerializedName("isSeasonRequiredToDrop")

    val isSeasonRequiredToDrop: Boolean,

    @SerializedName("name")

    val name: String,

    @SerializedName("randomAffixes")

    val randomAffixes: List<RandomAffix>,

    @SerializedName("requiredLevel")

    val requiredLevel: Long,

    @SerializedName("seasonRequiredToDrop")

    val seasonRequiredToDrop: Long,

    @SerializedName("setItems")

    val setItems: List<Any>,

    @SerializedName("slots")

    val slots: List<String>,

    @SerializedName("slug")

    val slug: String,

    @SerializedName("stackSizeMax")

    val stackSizeMax: Long,

    @SerializedName("tooltipParams")

    val tooltipParams: String,


    @SerializedName("type")

    val type: Type,

    @SerializedName("typeName")

    val typeName: String

)