package com.news.boundary.articles

data class Article(
    val title: String,
    val description: String,
    val publishedAt: Long,
    val imageUrl: String,
    val url: String
)