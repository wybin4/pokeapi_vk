package com.vk.usersapp.feature.feed.api

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vk.usersapp.core.Retrofit
import com.vk.usersapp.feature.feed.api.PokemonPagingSource.Companion.PAGE_SIZE
import com.vk.usersapp.feature.feed.model.PokemonListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokemonRepository {
    private val api: PokemonApi by lazy { Retrofit.getClient().create(PokemonApi::class.java) }
    fun listPokemon(): Flow<PagingData<PokemonListItem>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { PokemonPagingSource(api) }
        ).flow
    }

    fun getPokemon(id: Int) = flow {
        emit(api.getPokemon(id))
    }
}