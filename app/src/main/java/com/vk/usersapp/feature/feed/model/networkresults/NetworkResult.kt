package com.vk.usersapp.feature.feed.model.networkresults

sealed interface NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>
    data class Error(val exception: Throwable) : NetworkResult<Nothing>
    data object Loading : NetworkResult<Nothing>
}