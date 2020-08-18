package com.BlizzardArmory.model.warcraft.achievements.categories


import com.google.gson.annotations.SerializedName

data class NameLocalized(
        @SerializedName("de_DE")
        val deDE: String,
        @SerializedName("en_GB")
        val enGB: String,
        @SerializedName("en_US")
        val enUS: String,
        @SerializedName("es_ES")
        val esES: String,
        @SerializedName("es_MX")
        val esMX: String,
        @SerializedName("fr_FR")
        val frFR: String,
        @SerializedName("it_IT")
        val itIT: String,
        @SerializedName("ko_KR")
        val koKR: String,
        @SerializedName("pt_BR")
        val ptBR: String,
        @SerializedName("ru_RU")
        val ruRU: String,
        @SerializedName("zh_CN")
        val zhCN: String,
        @SerializedName("zh_TW")
        val zhTW: String
)