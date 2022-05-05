package com.news.di.navigation

import com.news.R
import com.news.interactors.articles.NavTarget
import com.news.navigation.NavigatorProvider
import com.news.presentation.Navigation
import javax.inject.Inject

class ArticlesNavigation @Inject constructor(
    private val router: NavigatorProvider
) : Navigation<NavTarget> {

    override fun navigate(target: NavTarget) {
        when (target) {
            is NavTarget.ArticleDetails -> router.provide()?.navigate(R.id.to_article_details)
        }
    }
}