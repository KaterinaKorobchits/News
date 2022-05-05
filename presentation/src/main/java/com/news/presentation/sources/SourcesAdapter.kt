package com.news.presentation.sources

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.news.interactors.sources.SourceItem
import com.news.presentation.databinding.ItemSourceBinding
import com.news.presentation.extensions.inflater
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class SourcesAdapter @Inject constructor() : ListAdapter<SourceItem, SourceHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<SourceItem>() {
        override fun areItemsTheSame(oldItem: SourceItem, newItem: SourceItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: SourceItem, newItem: SourceItem): Boolean =
            oldItem.name == newItem.name && oldItem.description == newItem.description
    }

    private val itemClick = MutableSharedFlow<SourceItem>(extraBufferCapacity = 1)
    val clicks = itemClick.asSharedFlow()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceHolder {
        val binding = ItemSourceBinding.inflate(parent.inflater(), parent, false)
        binding.root.setOnClickListener { binding.item?.let { itemClick.tryEmit(it) } }
        return SourceHolder(binding)
    }

    override fun onBindViewHolder(holder: SourceHolder, position: Int) {
        holder.bind(getItem(position))
    }
}