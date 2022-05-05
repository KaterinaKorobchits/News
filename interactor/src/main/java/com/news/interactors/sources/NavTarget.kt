package com.news.interactors.sources

sealed class NavTarget {

    data class Articles(val sourceId: String, val source: String) : NavTarget()
}