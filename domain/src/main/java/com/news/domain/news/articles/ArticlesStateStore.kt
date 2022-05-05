package com.news.domain.news.articles

import com.news.interactors.articles.ArticleItem

class ArticlesStateStore {

    private var source = ""
    private var selectedArticle: ArticleItem? = null

    fun source() = source

    fun source(source: String) {
        this.source = source
    }

    fun selectedArticle(source: ArticleItem) {
        this.selectedArticle = source
    }

    fun selectedArticle() = selectedArticle
}