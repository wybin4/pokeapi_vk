package com.vk.usersapp.feature.feed.ui.get

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vk.usersapp.feature.feed.api.PokemonRepository
import com.vk.usersapp.feature.feed.model.PokemonGet
import com.vk.usersapp.feature.feed.model.networkresults.NetworkResult
import com.vk.usersapp.feature.feed.model.networkresults.asNetworkResult
import com.vk.usersapp.feature.feed.model.state.GetState
import kotlinx.coroutines.launch

class PokemonGetViewModel: ViewModel() {
    private val _pokemon = MutableLiveData<PokemonGet>()
    val pokemon: LiveData<PokemonGet> = _pokemon

    private val _state = MutableLiveData<GetState>(GetState.Loading)
    val state: LiveData<GetState> = _state

    var id: Int = 0

    private val pokemonRepository = PokemonRepository()

    fun fetch() {
        if (id != 0) {
            viewModelScope.launch {
                pokemonRepository.getPokemon(id)
                    .asNetworkResult()
                    .collect { result ->
                        when (result) {
                            is NetworkResult.Success -> {
                                _state.value = GetState.Success
                                _pokemon.value = result.data
                            }
                            is NetworkResult.Loading -> {
                                _state.value = GetState.Loading
                            }
                            is NetworkResult.Error -> {
                                val errorMessage = result.exception.message
                                _state.value = errorMessage?.let { GetState.Error(it) }
                            }
                        }
                    }
            }
        } else {
            _state.value = GetState.Error("")
        }
    }

    fun setState(state: GetState) {
        _state.value = state
    }
}