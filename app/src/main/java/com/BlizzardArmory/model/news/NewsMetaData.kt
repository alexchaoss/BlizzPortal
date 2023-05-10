package com.BlizzardArmory.model.news

import androidx.annotation.Keep

@Keep
data class NewsMetaData(
    val url: String = "",
    val game: String = "",
    val imageUrl: String = "",
    val title: String = "",
    val description: String = "",
    val timeElapsed: String = "",
    val timestamp: String = ""
)