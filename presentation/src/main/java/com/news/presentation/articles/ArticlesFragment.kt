package com.news.presentation.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.news.interactors.articles.ArticlesInteractor
import com.news.interactors.articles.NavTarget
import com.news.presentation.Navigation
import com.news.presentation.databinding.FragmentArticlesBinding
import com.news.presentation.extensions.viewModelsFactory
import dagger.Lazy
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class ArticlesFragment : Fragment() {

    companion object {
        const val SOURCE_ID = "source_id"
        const val SOURCE = "source"
    }

    @Inject
    lateinit var interactor: Lazy<ArticlesInteractor>

    @Inject
    lateinit var form: Lazy<Form>

    @Inject
    lateinit var adapter: Provider<ArticlesAdapter>

    @Inject
    lateinit var navigation: Navigation<NavTarget>

    private val viewModel by viewModelsFactory { ArticlesViewModel(form.get(), interactor.get()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentArticlesBinding.inflate(inflater).also {
        it.lifecycleOwner = viewLifecycleOwner
        it.form = viewModel.form

        val sourceId = arguments?.getString(SOURCE_ID) ?: ""
        val source = arguments?.getString(SOURCE) ?: ""

        val adapter = adapter.get()
        it.rcv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
        }
        adapter.clicks
            .onEach { item ->
                viewModel.clickItem(item)
                navigation.navigate(NavTarget.ArticleDetails)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.init(sourceId, source)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.items.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }

            adapter.loadStateFlow.collectLatest { loadStates ->
                if (loadStates.refresh is LoadState.Error) println("Error TODO")
            }
        }
    }.root
}