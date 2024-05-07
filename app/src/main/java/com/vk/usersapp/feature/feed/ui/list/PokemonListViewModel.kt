package com.vk.usersapp.feature.feed.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vk.usersapp.feature.feed.api.PokemonRepository
import com.vk.usersapp.feature.feed.model.state.ListState
import com.vk.usersapp.feature.feed.model.networkresults.PagingNetworkResult
import com.vk.usersapp.feature.feed.model.PokemonListItem
import com.vk.usersapp.feature.feed.model.networkresults.asPagingNetworkResult
import kotlinx.coroutines.launch

class PokemonListViewModel: ViewModel() {
    private val _list = MutableLiveData<PagingData<PokemonListItem>>()
    val list: LiveData<PagingData<PokemonListItem>> = _list

    private val _listState = MutableLiveData<ListState>(ListState.Loading)
    val listState: LiveData<ListState> = _listState

    private val pokemonRepository = PokemonRepository()

    fun fetchList() {
        viewModelScope.launch {
            pokemonRepository.listPokemon()
                .cachedIn(viewModelScope)
                .asPagingNetworkResult()
                .collect { result ->
                    when (result) {
                        is PagingNetworkResult.Loading -> {
                            if (result.data != null) {
                                _list.value = result.data!!
                            }
                        }
                        else -> {}
                    }
                }
        }
    }

    fun setState(state: ListState) {
        _listState.value = state
    }
}