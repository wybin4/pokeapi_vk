package com.vk.usersapp.feature.feed.api

import com.vk.usersapp.feature.feed.model.PokemonGet
import com.vk.usersapp.feature.feed.model.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon/")
    suspend fun listPokemon(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonList

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int): PokemonGet
}