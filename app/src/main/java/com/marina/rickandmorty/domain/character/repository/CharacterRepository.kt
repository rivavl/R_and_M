package com.marina.rickandmorty.domain.character.repository

import com.marina.rickandmorty.data.character.entity.CharacterDto
import com.marina.rickandmorty.data.character.entity.CharacterResponse

interface CharacterRepository {

    suspend fun getAllCharacters(pageNumber: Int): List<CharacterDto>

    suspend fun getCharacter(id: Int): CharacterDto
}