package com.news.di.articles.details

import com.news.di.articles.DetailsScope
import com.news.domain.news.articles.ArticleDetailsInteractorImpl
import com.news.interactors.articles.ArticleDetailsInteractor
import dagger.Binds
import dagger.Module

@Module
interface ArticleDetailsModule {

    @Binds
    @DetailsScope
    fun provideInteractor(interactor: ArticleDetailsInteractorImpl): ArticleDetailsInteractor
}