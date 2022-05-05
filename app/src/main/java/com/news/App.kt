package com.news

import android.app.Application
import android.os.StrictMode
import com.news.di.app.AppComponent
import com.news.di.app.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        setupDi()
    }

    private fun setupDi() {
        appComponent = DaggerAppComponent.builder()
            .build()
    }
}