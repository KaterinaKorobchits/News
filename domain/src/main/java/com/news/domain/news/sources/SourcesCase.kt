package com.news.domain.news.sources

import com.news.boundary.sources.SourcesRepository
import com.news.interactors.sources.SourcesResult
import javax.inject.Inject

class SourcesCase @Inject constructor(
    private val sourcesRepository: SourcesRepository
) {

    suspend operator fun invoke() = try {
        sourcesRepository.sources().fold(
            onSuccess = { result -> SourcesResult.Sources(result.map { it.toSourceItem() }) },
            onFailure = { SourcesResult.Error(it) }
        )
    } catch (ex: Exception) {
        SourcesResult.Error(ex)
    }
}