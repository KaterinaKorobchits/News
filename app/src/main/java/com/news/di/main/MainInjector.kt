package com.news.di.main

import androidx.fragment.app.Fragment
import com.news.di.articles.ArticlesComponent
import com.news.di.articles.DaggerArticlesComponent
import com.news.di.articles.details.DaggerArticleDetailsComponent
import com.news.di.sources.DaggerSourcesComponent
import com.news.presentation.articles.ArticlesFragment
import com.news.presentation.articles.details.ArticleDetailsFragment
import com.news.presentation.sources.SourcesFragment

class MainInjector {

    private var articlesComponent: ArticlesComponent? = null

    fun inject(fragment: Fragment, mainComponent: MainComponent) {
        when (fragment) {
            is SourcesFragment -> DaggerSourcesComponent.builder()
                .mainComponent(mainComponent)
                .build()
                .inject(fragment)
            is ArticlesFragment -> DaggerArticlesComponent.builder()
                .mainComponent(mainComponent)
                .build()
                .apply { articlesComponent = this }
                .inject(fragment)
            is ArticleDetailsFragment -> DaggerArticleDetailsComponent.builder()
                .articlesComponent(articlesComponent)
                .build()
                .inject(fragment)
        }
    }
}