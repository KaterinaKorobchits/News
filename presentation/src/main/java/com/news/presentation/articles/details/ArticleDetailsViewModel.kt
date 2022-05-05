package com.news.presentation.articles.details

import androidx.lifecycle.ViewModel
import com.news.interactors.articles.ArticleDetailsInteractor

class ArticleDetailsViewModel(
    val form: Form,
    interactor: ArticleDetailsInteractor
) : ViewModel() {

    init {
        form.article.set(interactor.article())
    }
}