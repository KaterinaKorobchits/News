package com.news

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.news.di.main.MainComponent
import com.news.di.main.MainInjector

class MainViewModel(
    private val injector: MainInjector,
    private val component: MainComponent
) : ViewModel() {

    fun inject(fragment: Fragment) = injector.inject(fragment, component)

    fun inject(activity: MainActivity) = component.inject(activity)
}