package com.news.presentation.articles.details

import androidx.databinding.ObservableField
import com.news.interactors.articles.ArticleItem
import javax.inject.Inject

class Form @Inject constructor() {
    val article = ObservableField<ArticleItem>()
}