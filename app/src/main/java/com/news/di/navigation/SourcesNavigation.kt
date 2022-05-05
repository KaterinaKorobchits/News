package com.news.di.navigation

import android.os.Bundle
import com.news.R
import com.news.interactors.sources.NavTarget
import com.news.navigation.NavigatorProvider
import com.news.presentation.Navigation
import com.news.presentation.articles.ArticlesFragment.Companion.SOURCE
import com.news.presentation.articles.ArticlesFragment.Companion.SOURCE_ID
import javax.inject.Inject

class SourcesNavigation @Inject constructor(
    private val router: NavigatorProvider
) : Navigation<NavTarget> {

    override fun navigate(target: NavTarget) {
        when (target) {
            is NavTarget.Articles -> {
                val bundle = Bundle().apply {
                    putString(SOURCE_ID, target.sourceId)
                    putString(SOURCE, target.source)
                }
                router.provide()?.navigate(R.id.to_articles, bundle)
            }
        }
    }
}