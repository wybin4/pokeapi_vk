package com.vk.usersapp.feature.feed.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.vk.usersapp.R
import com.vk.usersapp.databinding.PokemonListBinding
import com.vk.usersapp.feature.feed.model.state.ListState
import com.vk.usersapp.feature.feed.model.state.handleLoadState
import kotlinx.coroutines.launch

class PokemonListView : Fragment() {
    private var _binding: PokemonListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { ViewModelProvider(this)[PokemonListViewModel::class.java] }

    private lateinit var listAdapter: PokemonPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PokemonListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        setupAdapter()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeResourceState()
        observeList()
    }

    private fun observeList() {
        viewModel.list.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                listAdapter.submitData(it)
            }
        }
    }

    private fun observeResourceState() {
        viewModel.listState.observe(viewLifecycleOwner) { resource ->
            updateViewState(resource)
        }
        listAdapter.addLoadStateListener {
            it.handleLoadState(viewModel::setState, listAdapter.itemCount < 1)
        }
    }

    private fun updateViewState(state: ListState) {
        binding.apply {
            loader.visibility = state.loadingVisibility
            recycler.visibility = state.successVisibility
            error.visibility = state.emptyVisibility
            error.visibility = state.errorVisibility
            state.errorMessage?.let { error.error = it }
        }
    }

    private fun setupAdapter() {
        listAdapter = PokemonPagingAdapter{
            val bundle = bundleOf("id" to it.getId())
            findNavController().navigate(R.id.action_PokemonListView_to_PokemonGetView, bundle)
        }
        val headerAdapter = TextHeaderAdapter()
        val concatAdapter = ConcatAdapter(headerAdapter, listAdapter)
        binding.recycler.adapter = concatAdapter
        val layoutManager = GridLayoutManager(requireActivity(), 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0) {
                    layoutManager.spanCount
                } else {
                    1
                }
            }
        }
        binding.recycler.layoutManager = layoutManager
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}