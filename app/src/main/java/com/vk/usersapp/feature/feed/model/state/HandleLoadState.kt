package com.vk.usersapp.feature.feed.model.state

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState

fun CombinedLoadStates.handleLoadState(resourceCallback: (ListState) -> Unit, isItemCountNullable: Boolean) {
    when (refresh) {
        is LoadState.Error -> {
            val errorMessage = (refresh as LoadState.Error).error.message
            resourceCallback(ListState.Error(errorMessage ?: ""))
        }
        else -> {}
    }

    if (source.refresh is LoadState.NotLoading && append.endOfPaginationReached && isItemCountNullable) {
        resourceCallback(ListState.Empty)
    }

    if (source.refresh is LoadState.NotLoading && !append.endOfPaginationReached && !isItemCountNullable) {
        resourceCallback(ListState.Success)
    }
}