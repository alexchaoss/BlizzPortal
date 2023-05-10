package com.BlizzardArmory.model.news

import androidx.annotation.Keep

@Keep
data class NewsPage(
    val game: String,
    val title: String,
    val author: String,
    val date: String,
    val imageURL: String,
    val body: String
)