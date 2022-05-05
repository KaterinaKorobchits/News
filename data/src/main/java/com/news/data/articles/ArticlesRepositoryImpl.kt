package com.news.data.articles

import com.news.boundary.articles.Article
import com.news.boundary.articles.ArticlesRepository
import com.news.data.IoDispatcher
import com.news.data.NewsService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
    private val service: NewsService,
    @IoDispatcher
    private val dispatcher: CoroutineDispatcher
) : ArticlesRepository {

    override suspend fun articles(source: String, page: Int): Result<List<Article>> =
        withContext(dispatcher) {
            service.articles(source, page).fold(
                onSuccess = { result -> Result.success(result.articles.map { it.toArticle() }) },
                onFailure = { Result.failure(it) }
            )
        }
}