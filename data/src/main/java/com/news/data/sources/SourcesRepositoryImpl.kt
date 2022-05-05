package com.news.data.sources

import com.news.boundary.sources.Source
import com.news.boundary.sources.SourcesRepository
import com.news.data.IoDispatcher
import com.news.data.NewsService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor(
    private val service: NewsService,
    @IoDispatcher
    private val dispatcher: CoroutineDispatcher
) : SourcesRepository {

    override suspend fun sources(): Result<List<Source>> = withContext(dispatcher) {
        service.sources().fold(
            onSuccess = { it -> Result.success(it.sources.map { it.toSource() }) },
            onFailure = { Result.failure(it) }
        )
    }
}