package com.news.interactors.sources

interface SourcesInteractor {

    suspend fun sources(): SourcesResult
}