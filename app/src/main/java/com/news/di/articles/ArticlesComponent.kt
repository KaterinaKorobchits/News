package com.news.di.articles

import com.news.di.FragmentScope
import com.news.di.main.MainComponent
import com.news.domain.news.articles.ArticlesStateStore
import com.news.navigation.NavigatorProvider
import com.news.presentation.articles.ArticlesFragment
import dagger.Component

@FragmentScope
@Component(
    dependencies = [MainComponent::class],
    modules = [ArticlesModule::class]
)
interface ArticlesComponent {

    fun stateStore(): ArticlesStateStore

    fun navigatorProvider(): NavigatorProvider

    fun inject(fragment: ArticlesFragment)
}