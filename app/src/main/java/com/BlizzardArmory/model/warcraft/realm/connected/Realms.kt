package com.BlizzardArmory.model.warcraft.realm.connected

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Realms(

    @SerializedName("is_tournament") val is_tournament: Boolean,
    @SerializedName("timezone") val timezone: String,
    @SerializedName("name") val name: Name,
    @SerializedName("id") val id: Int,
    @SerializedName("region") val region: Region,
    @SerializedName("category") val category: Category,
    @SerializedName("locale") val locale: String,
    @SerializedName("type") val type: Type,
    @SerializedName("slug") val slug: String
)