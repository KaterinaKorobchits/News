package com.news.domain.news.articles

import com.news.interactors.articles.ArticleDetailsInteractor
import javax.inject.Inject

class ArticleDetailsInteractorImpl @Inject constructor(
    private val stateStore: ArticlesStateStore
) : ArticleDetailsInteractor {

    override fun article() = stateStore.selectedArticle()
}