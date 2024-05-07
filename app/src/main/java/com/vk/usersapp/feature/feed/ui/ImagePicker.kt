package com.vk.usersapp.feature.feed.ui

interface ImagePicker {
    fun getImageUrlBase(): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"
    }
}