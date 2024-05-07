package com.vk.usersapp.feature.feed.model.state

import android.view.View

sealed class GetState {
    data object Success : GetState()
    data object Loading : GetState()
    data class Error(val message: String) : GetState()
    val loadingVisibility: Int
        get() = if (this is Loading) View.VISIBLE else View.GONE

    val errorVisibility: Int
        get() = if (this is Error) View.VISIBLE else View.GONE

    val successVisibility: Int
        get() = if (this is Success) View.VISIBLE else View.GONE

    val errorMessage: String?
        get() = if (this is Error) message else null
}