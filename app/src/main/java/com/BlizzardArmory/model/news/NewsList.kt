package com.BlizzardArmory.model.news

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class NewsList(
    @SerializedName("html") val html: String,
    @SerializedName("pageType") val pageType: String,
    @SerializedName("pageTitle") val pageTitle: String,
    @SerializedName("pageNum") val pageNum: Int,
    @SerializedName("pageSize") val pageSize: Int,
    @SerializedName("totalCount") val totalCount: Int
)