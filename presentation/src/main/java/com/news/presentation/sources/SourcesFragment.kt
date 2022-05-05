package com.news.presentation.sources

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.news.interactors.sources.NavTarget
import com.news.interactors.sources.SourcesInteractor
import com.news.presentation.Navigation
import com.news.presentation.databinding.FragmentSourcesBinding
import com.news.presentation.extensions.viewModelsFactory
import dagger.Lazy
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Provider

class SourcesFragment : Fragment() {

    @Inject
    lateinit var interactor: Lazy<SourcesInteractor>

    @Inject
    lateinit var adapter: Provider<SourcesAdapter>

    @Inject
    lateinit var navigation: Navigation<NavTarget>

    private val viewModel by viewModelsFactory { SourcesViewModel(interactor.get()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSourcesBinding.inflate(inflater, container, false)

        val adapter = adapter.get()
        binding.rcv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
        }
        adapter.clicks
            .onEach { source -> navigation.navigate(NavTarget.Articles(source.id, source.name)) }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.sources
            .onEach { adapter.submitList(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        return binding.root
    }
}