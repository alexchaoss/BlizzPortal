package com.BlizzardArmory.model.warcraft.realm.connected

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Category(

    @SerializedName("it_IT") val it_IT: String,
    @SerializedName("ru_RU") val ru_RU: String,
    @SerializedName("en_GB") val en_GB: String,
    @SerializedName("zh_TW") val zh_TW: String,
    @SerializedName("ko_KR") val ko_KR: String,
    @SerializedName("en_US") val en_US: String,
    @SerializedName("es_MX") val es_MX: String,
    @SerializedName("pt_BR") val pt_BR: String,
    @SerializedName("es_ES") val es_ES: String,
    @SerializedName("zh_CN") val zh_CN: String,
    @SerializedName("fr_FR") val fr_FR: String,
    @SerializedName("de_DE") val de_DE: String
)