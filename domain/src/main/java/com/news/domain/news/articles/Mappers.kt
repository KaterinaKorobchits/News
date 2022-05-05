package com.news.domain.news.articles

import com.news.boundary.articles.Article
import com.news.interactors.articles.ArticleItem
import java.text.SimpleDateFormat
import java.util.*

internal fun Article.toArticleItem(): ArticleItem {
    val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault()).apply {
        timeZone = TimeZone.getDefault()
    }.format(publishedAt)
    return ArticleItem(title, description, dateFormat, imageUrl, url)
}

