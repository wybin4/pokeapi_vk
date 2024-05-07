package com.vk.usersapp.feature.feed.ui

import java.util.Locale

interface CapLetterPicker {
    fun getCap(string: String): String {
        return string.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }
    }
}