package com.news.presentation.articles

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.news.interactors.articles.ArticleItem
import com.news.interactors.articles.ArticlesInteractor
import com.news.interactors.articles.ArticlesPageResult
import javax.inject.Inject

class ArticlesPagingDataSource @Inject constructor(
    private val interactor: ArticlesInteractor
) : PagingSource<Int, ArticleItem>() {

    override fun getRefreshKey(state: PagingState<Int, ArticleItem>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleItem> {
        val page = params.key ?: 1

        return when (val data = interactor.articles(interactor.source(), page)) {
            is ArticlesPageResult.Articles -> {
                val nextKey = if (data.items.isEmpty()) null else page + 1
                val prevKey = if (page > 1) page - 1 else null
                LoadResult.Page(data.items, prevKey, nextKey)
            }
            is ArticlesPageResult.Error -> LoadResult.Error(data.throwable)
        }
    }
}