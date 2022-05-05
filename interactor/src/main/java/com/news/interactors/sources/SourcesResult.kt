package com.news.interactors.sources

sealed class SourcesResult {

    data class Sources(val items: List<SourceItem>) : SourcesResult()

    data class Error(val throwable: Throwable) : SourcesResult()
}