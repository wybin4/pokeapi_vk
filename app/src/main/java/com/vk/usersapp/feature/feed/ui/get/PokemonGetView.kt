package com.vk.usersapp.feature.feed.ui.get

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vk.usersapp.databinding.PokemonGetBinding
import com.vk.usersapp.feature.feed.model.state.GetState

class PokemonGetView : Fragment() {
    private var _binding: PokemonGetBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { ViewModelProvider(this)[PokemonGetViewModel::class.java] }
    private lateinit var typeAdapter: TypeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PokemonGetBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        viewModel.id = arguments?.getInt("id", 0) ?: 0

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetch()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        observeResourceState()
        observeList()
    }

    private fun observeList() {
        viewModel.pokemon.observe(viewLifecycleOwner) {
            val items = it.getFlatTypeNames()
            typeAdapter.submitList(items)
        }
    }

    private fun setupAdapter() {
        typeAdapter = TypeAdapter(requireActivity())
        binding.typeList.adapter = typeAdapter

        val layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.typeList.layoutManager = layoutManager
    }

    private fun observeResourceState() {
        viewModel.state.observe(viewLifecycleOwner) { resource ->
            updateViewState(resource)
        }
    }

    private fun updateViewState(state: GetState) {
        binding.apply {
            loader.visibility = state.loadingVisibility
            success.visibility = state.successVisibility
            error.visibility = state.errorVisibility
            state.errorMessage?.let { error.error = it }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}