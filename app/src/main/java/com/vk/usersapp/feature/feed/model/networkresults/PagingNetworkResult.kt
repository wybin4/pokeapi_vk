package com.vk.usersapp.feature.feed.model.networkresults

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface PagingNetworkResult<out T> {
    data class Error(val exception: Throwable) : PagingNetworkResult<Nothing>
    data class Loading<T>(val data: T?) : PagingNetworkResult<T>
}


