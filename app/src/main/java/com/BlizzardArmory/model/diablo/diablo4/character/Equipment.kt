package com.BlizzardArmory.model.diablo.diablo4.character

import com.google.gson.annotations.SerializedName


data class Equipment(
    @SerializedName("name") var name: String? = null,
    @SerializedName("tex") var tex: Long? = null,
    @SerializedName("itemtype") var itemtype: String? = null,
    @SerializedName("power") var power: Int? = null,
    @SerializedName("quality") var quality: String? = null,
    @SerializedName("base_affixes") var baseAffixes: ArrayList<String> = arrayListOf(),
    @SerializedName("strikethrough_affixes") var strikethroughAffixes: ArrayList<String> = arrayListOf(),
    @SerializedName("added_affixes") var addedAffixes: ArrayList<String> = arrayListOf()
)