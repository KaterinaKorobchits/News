package com.news.interactors.articles

interface ArticlesInteractor {

    fun source(): String

    fun source(source: String)

    fun selectArticle(source: ArticleItem)

    suspend fun articles(source: String, page: Int): ArticlesPageResult
}