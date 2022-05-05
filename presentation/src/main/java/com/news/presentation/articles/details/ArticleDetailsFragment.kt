package com.news.presentation.articles.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.news.interactors.articles.ArticleDetailsInteractor
import com.news.presentation.databinding.FragmentArticleDetailsBinding
import com.news.presentation.extensions.viewModelsFactory
import dagger.Lazy
import javax.inject.Inject

class ArticleDetailsFragment : Fragment() {

    @Inject
    lateinit var form: Lazy<Form>

    @Inject
    lateinit var interactor: Lazy<ArticleDetailsInteractor>

    private val viewModel by viewModelsFactory {
        ArticleDetailsViewModel(form.get(), interactor.get())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentArticleDetailsBinding.inflate(inflater).also {
        it.lifecycleOwner = viewLifecycleOwner
        it.form = viewModel.form
    }.root
}