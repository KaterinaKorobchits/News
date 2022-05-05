package com.news.di.articles

import com.news.boundary.articles.ArticlesRepository
import com.news.data.articles.ArticlesRepositoryImpl
import com.news.di.FragmentScope
import com.news.di.navigation.ArticlesNavigation
import com.news.domain.news.articles.ArticlesInteractorImpl
import com.news.domain.news.articles.ArticlesStateStore
import com.news.interactors.articles.ArticlesInteractor
import com.news.interactors.articles.NavTarget
import com.news.presentation.Navigation
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface ArticlesModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @FragmentScope
        fun provideStateStore() = ArticlesStateStore()
    }

    @Binds
    @FragmentScope
    fun provideInteractor(interactor: ArticlesInteractorImpl): ArticlesInteractor

    @Binds
    @FragmentScope
    fun provideSourcesRepository(interactor: ArticlesRepositoryImpl): ArticlesRepository

    @Binds
    @FragmentScope
    fun provideNavigation(navigation: ArticlesNavigation): Navigation<NavTarget>
}