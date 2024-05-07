package com.vk.usersapp.feature.feed.ui

import android.content.Context
import androidx.core.content.ContextCompat
import com.vk.usersapp.R

interface ColorPicker {
    val ColorPokemonTypeMap: Map<String, Int>
        get() = mapOf(
            "normal" to R.color.normal,
            "fire" to R.color.fire,
            "water" to R.color.water,
            "electric" to R.color.electric,
            "grass" to R.color.grass,
            "ice" to R.color.ice,
            "fighting" to R.color.fighting,
            "poison" to R.color.poison,
            "ground" to R.color.ground,
            "flying" to R.color.flying,
            "psychic" to R.color.psychic,
            "bug" to R.color.bug,
            "rock" to R.color.rock,
            "ghost" to R.color.ghost,
            "dragon" to R.color.dragon,
            "dark" to R.color.dark,
            "steel" to R.color.steel,
            "fairy" to R.color.fairy
        )

    fun getColor(context: Context, name: String): Int {
        val colorResId = ColorPokemonTypeMap[name] ?: throw IllegalArgumentException("Color not found for $name")
        return ContextCompat.getColor(context, colorResId)
    }
}