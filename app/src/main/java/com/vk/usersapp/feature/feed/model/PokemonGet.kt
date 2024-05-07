package com.vk.usersapp.feature.feed.model

import android.content.Context
import com.vk.usersapp.R
import com.vk.usersapp.feature.feed.ui.CapLetterPicker
import com.vk.usersapp.feature.feed.ui.ColorPicker
import com.vk.usersapp.feature.feed.ui.ImagePicker

data class PokemonGet(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<Type>
): ImagePicker, CapLetterPicker, ColorPicker {
    fun getFormattedId(): String {
        return id.toString()
    }

    fun getFormattedHeight(context: Context): String {
        return context.getString(R.string.height) + ":\u0020" + height + " " + context.getString(R.string.centimetre)
    }

    fun getFormattedWeight(context: Context): String {
        return context.getString(R.string.weight) + ":\u0020" + weight + " " + context.getString(R.string.kilogram)
    }

    fun getImageUrl(): String {
        return "${getImageUrlBase()}${id}.png"
    }

    fun getCapName(): String {
        return getCap(name)
    }

    fun getFlatTypeNames(): List<String> {
        return types.map { getCap(it.type.name) }
    }

}