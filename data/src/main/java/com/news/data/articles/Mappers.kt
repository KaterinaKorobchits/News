package com.news.data.articles

import com.news.boundary.articles.Article
import java.text.SimpleDateFormat
import java.util.*

internal fun ArticleDto.toArticle(): Article {
    val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }.parse(publishedAt) ?: Date()
    return Article(title, description, date.time, if (urlToImage == "null") "" else urlToImage, url)
}

