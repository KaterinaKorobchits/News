package com.news.boundary.articles

interface ArticlesRepository {

    suspend fun articles(source: String, page: Int): Result<List<Article>>
}