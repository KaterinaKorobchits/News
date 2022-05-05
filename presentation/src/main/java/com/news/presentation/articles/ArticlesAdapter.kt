package com.news.presentation.articles

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.news.interactors.articles.ArticleItem
import com.news.presentation.databinding.ItemArticleBinding
import com.news.presentation.extensions.inflater
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class ArticlesAdapter @Inject constructor() :
    PagingDataAdapter<ArticleItem, ArticleHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<ArticleItem>() {
        override fun areItemsTheSame(oldItem: ArticleItem, newItem: ArticleItem) =
            oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: ArticleItem, newItem: ArticleItem) =
            oldItem == newItem
    }

    private val itemClick = MutableSharedFlow<ArticleItem>(extraBufferCapacity = 1)
    val clicks = itemClick.asSharedFlow()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        val binding = ItemArticleBinding.inflate(parent.inflater(), parent, false)
        binding.root.setOnClickListener { binding.item?.let { itemClick.tryEmit(it) } }
        return ArticleHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}