package com.news.presentation.sources

import androidx.recyclerview.widget.RecyclerView
import com.news.interactors.sources.SourceItem
import com.news.presentation.databinding.ItemSourceBinding

class SourceHolder(
    private val binding: ItemSourceBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SourceItem) {
        binding.item = item
    }
}