package com.news.domain.news.sources

import com.news.boundary.sources.Source
import com.news.interactors.sources.SourceItem

internal fun Source.toSourceItem() = SourceItem(id, name, description)
