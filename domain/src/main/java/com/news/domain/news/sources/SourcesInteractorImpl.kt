package com.news.domain.news.sources

import com.news.interactors.sources.SourcesInteractor
import javax.inject.Inject

class SourcesInteractorImpl @Inject constructor(
    private val sourcesCase: SourcesCase
) : SourcesInteractor {

    override suspend fun sources() = sourcesCase()
}