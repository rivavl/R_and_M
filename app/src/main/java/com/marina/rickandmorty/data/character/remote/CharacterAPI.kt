package com.marina.rickandmorty.data.character.remote

import com.marina.rickandmorty.data.character.entity.CharacterDto
import com.marina.rickandmorty.data.character.entity.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterAPI {

    @GET("character/")
    suspend fun getCharacters(
        @Query("page")
        pageNumber: Int = 1
    ): CharacterResponse

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): CharacterDto
}