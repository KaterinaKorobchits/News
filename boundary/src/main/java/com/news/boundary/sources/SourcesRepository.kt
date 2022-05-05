package com.news.boundary.sources

interface SourcesRepository {

    suspend fun sources(): Result<List<Source>>
}