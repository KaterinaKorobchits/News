package com.news.presentation.sources

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.interactors.sources.SourceItem
import com.news.interactors.sources.SourcesInteractor
import com.news.interactors.sources.SourcesResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SourcesViewModel(
    private val interactor: SourcesInteractor
) : ViewModel() {

    private val mutSources = MutableStateFlow(emptyList<SourceItem>())
    val sources: StateFlow<List<SourceItem>> = mutSources.asStateFlow()

    init {
        viewModelScope.launch {
            when(val result = interactor.sources()){
                is SourcesResult.Sources -> mutSources.value = result.items
                is SourcesResult.Error -> println(result.throwable)
            }
        }
    }
}