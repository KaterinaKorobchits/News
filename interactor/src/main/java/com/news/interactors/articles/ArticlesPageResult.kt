package com.news.interactors.articles

sealed class ArticlesPageResult {

    data class Articles(val items: List<ArticleItem>) : ArticlesPageResult()

    data class Error(val throwable: Throwable) : ArticlesPageResult()
}