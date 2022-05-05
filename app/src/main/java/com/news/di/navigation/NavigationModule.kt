package com.news.di.navigation

import com.news.di.ActivityScope
import com.news.navigation.Navigator
import com.news.navigation.NavigatorBinder
import com.news.navigation.NavigatorProvider
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface NavigationModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @ActivityScope
        fun provideNavigator() = Navigator()
    }

    @Binds
    @ActivityScope
    fun provideNavBinder(navigator: Navigator): NavigatorBinder

    @Binds
    @ActivityScope
    fun providerRouter(navigator: Navigator): NavigatorProvider
}