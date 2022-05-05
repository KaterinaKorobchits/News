package com.news.domain.news.articles

import com.news.interactors.articles.ArticleItem
import com.news.interactors.articles.ArticlesInteractor
import javax.inject.Inject

class ArticlesInteractorImpl @Inject constructor(
    private val sourcesCase: ArticlesPageCase,
    private val stateStore: ArticlesStateStore
) : ArticlesInteractor {

    override fun source() = stateStore.source()

    override fun source(source: String) = stateStore.source(source)

    override fun selectArticle(source: ArticleItem) = stateStore.selectedArticle(source)

    override suspend fun articles(source: String, page: Int) = sourcesCase(source, page)
}