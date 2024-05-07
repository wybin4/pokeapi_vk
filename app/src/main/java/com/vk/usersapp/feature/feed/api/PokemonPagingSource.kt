package com.vk.usersapp.feature.feed.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vk.usersapp.feature.feed.model.PokemonListItem

class PokemonPagingSource(private val api: PokemonApi) : PagingSource<Int, PokemonListItem>() {
    companion object {
        const val STARTING_PAGE_INDEX = 0
        const val PAGE_SIZE = 30
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonListItem> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val request = api.listPokemon(offset = page, limit = PAGE_SIZE * page).results
            LoadResult.Page(
                data = request,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (request.isEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonListItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}