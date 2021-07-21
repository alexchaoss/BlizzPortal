package com.BlizzardArmory.model.news

data class NewsPage(
    val game: String,
    val title: String,
    val author: String,
    val date: String,
    val imageURL: String,
    val body: String
)