package com.vk.usersapp.feature.feed.model

import com.vk.usersapp.feature.feed.ui.CapLetterPicker
import com.vk.usersapp.feature.feed.ui.ImagePicker

data class PokemonListItem(
    val name: String,
    val url: String
): ImagePicker, CapLetterPicker {
    fun getCapName(): String {
        return getCap(name)
    }

    fun getId(): Int {
        val pattern = """/(\d+)/?$""".toRegex()
        val match = pattern.find(url)
        val pokemonNumber = match?.value?.substringBeforeLast("/") ?: "0"
        return Integer.parseInt(pokemonNumber.replace("/", ""))
    }

    fun getImageUrl(): String {
        return "${getImageUrlBase()}${getId()}.png"
    }

}