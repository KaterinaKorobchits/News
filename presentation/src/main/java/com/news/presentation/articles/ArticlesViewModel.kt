package com.news.presentation.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.news.interactors.articles.ArticleItem
import com.news.interactors.articles.ArticlesInteractor

class ArticlesViewModel(
    val form: Form,
    private val interactor: ArticlesInteractor
) : ViewModel() {

    internal val items = Pager(PagingConfig(pageSize = 20, prefetchDistance = 10)) {
        ArticlesPagingDataSource(interactor)
    }
        .flow
        .cachedIn(viewModelScope)

    fun init(sourceId: String, source: String) {
        form.source.set(source)
        interactor.source(sourceId)
    }

    fun clickItem(item: ArticleItem) = interactor.selectArticle(item)
}