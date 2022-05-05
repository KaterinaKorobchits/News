package com.news.di.articles.details

import com.news.di.articles.ArticlesComponent
import com.news.di.articles.DetailsScope
import com.news.presentation.articles.details.ArticleDetailsFragment
import dagger.Component

@DetailsScope
@Component(
    dependencies = [ArticlesComponent::class],
    modules = [ArticleDetailsModule::class]
)
interface ArticleDetailsComponent {

    fun inject(fragment: ArticleDetailsFragment)
}