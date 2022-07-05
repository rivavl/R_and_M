package com.marina.rickandmorty.domain.character.repository

import com.marina.rickandmorty.data.character.entity.CharacterDto

interface CharacterRepository {

    suspend fun getAllCharacters(pageNumber: Int): List<CharacterDto>

    suspend fun getCharacter(id: Int): CharacterDto

    suspend fun getCharacters(ids: String): List<CharacterDto>
}