package com.news.domain.news.articles

import com.news.boundary.articles.ArticlesRepository
import com.news.interactors.articles.ArticlesPageResult
import javax.inject.Inject

class ArticlesPageCase @Inject constructor(
    private val sourcesRepository: ArticlesRepository
) {

    suspend operator fun invoke(source: String, page: Int) = try {
        sourcesRepository.articles(source, page).fold(
            onSuccess = { result -> ArticlesPageResult.Articles(result.map { it.toArticleItem() }) },
            onFailure = { ArticlesPageResult.Error(it) }
        )
    } catch (ex: Exception) {
        ArticlesPageResult.Error(ex)
    }
}