package com.vk.usersapp.feature.feed.model.networkresults

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

fun <T> Flow<T>.asPagingNetworkResult(): Flow<PagingNetworkResult<T>> = map<T, PagingNetworkResult<T>> {
    PagingNetworkResult.Loading(
        it
    )
}
    .onStart { emit(PagingNetworkResult.Loading(null)) }
    .catch { emit(PagingNetworkResult.Error(it)) }
