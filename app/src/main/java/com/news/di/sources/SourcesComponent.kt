package com.news.di.sources

import com.news.di.FragmentScope
import com.news.di.main.MainComponent
import com.news.navigation.NavigatorProvider
import com.news.presentation.sources.SourcesFragment
import dagger.Component

@FragmentScope
@Component(
    dependencies = [MainComponent::class],
    modules = [SourcesModule::class]
)
interface SourcesComponent {

    fun navigatorProvider(): NavigatorProvider

    fun inject(fragment: SourcesFragment)
}