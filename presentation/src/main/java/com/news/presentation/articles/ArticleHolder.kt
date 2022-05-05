package com.news.presentation.articles

import androidx.recyclerview.widget.RecyclerView
import com.news.interactors.articles.ArticleItem
import com.news.presentation.databinding.ItemArticleBinding

class ArticleHolder(
    private val binding: ItemArticleBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ArticleItem) {
        binding.item = item
    }
}