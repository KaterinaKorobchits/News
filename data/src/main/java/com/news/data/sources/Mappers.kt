package com.news.data.sources

import com.news.boundary.sources.Source

internal fun SourceDto.toSource() = Source(id, name, description)
