package com.news.di.main

import com.news.MainActivity
import com.news.data.IoDispatcher
import com.news.data.NewsService
import com.news.di.ActivityScope
import com.news.di.app.AppComponent
import com.news.di.navigation.NavigationModule
import com.news.navigation.NavigatorProvider
import dagger.Component
import kotlinx.coroutines.CoroutineDispatcher

@ActivityScope
@Component(
    modules = [NavigationModule::class],
    dependencies = [AppComponent::class]
)
interface MainComponent {

    @IoDispatcher
    fun io(): CoroutineDispatcher

    fun newsService(): NewsService

    fun navigatorProvider(): NavigatorProvider

    fun inject(activity: MainActivity)
}