package com.news.di.sources

import com.news.boundary.sources.SourcesRepository
import com.news.data.sources.SourcesRepositoryImpl
import com.news.di.FragmentScope
import com.news.di.navigation.SourcesNavigation
import com.news.domain.news.sources.SourcesInteractorImpl
import com.news.interactors.sources.NavTarget
import com.news.interactors.sources.SourcesInteractor
import com.news.presentation.Navigation
import dagger.Binds
import dagger.Module

@Module
interface SourcesModule {

    @Binds
    @FragmentScope
    fun provideInteractor(interactor: SourcesInteractorImpl): SourcesInteractor

    @Binds
    @FragmentScope
    fun provideSourcesRepository(interactor: SourcesRepositoryImpl): SourcesRepository

    @Binds
    @FragmentScope
    fun provideNavigation(navigation: SourcesNavigation): Navigation<NavTarget>
}