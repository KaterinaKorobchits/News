package com.news.di.app

import com.news.data.IoDispatcher
import com.news.data.NewsService
import com.news.di.rest.RestModule
import dagger.Component
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Singleton
@Component(modules = [RestModule::class, CoroutinesModule::class])
interface AppComponent{

    @IoDispatcher
    fun io(): CoroutineDispatcher

    fun newsService(): NewsService
}