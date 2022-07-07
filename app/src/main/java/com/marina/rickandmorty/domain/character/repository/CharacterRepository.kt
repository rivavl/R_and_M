package com.marina.rickandmorty.domain.character.repository

import com.marina.rickandmorty.data.character.local.entity.CharacterDB
import com.marina.rickandmorty.data.character.remote.entity.CharacterDto
import com.marina.rickandmorty.data.episode.local.EpisodeDB

interface CharacterRepository {

    suspend fun getAllCharacters(pageNumber: Int): List<CharacterDto>

    suspend fun getCharacter(id: Int): List<CharacterDto>

    suspend fun getCharacters(ids: String): List<CharacterDto>

    suspend fun getCharactersDB(pageNumber: Int): List<CharacterDB>

    suspend fun getCharacterDB(id: Int): CharacterDB

    suspend fun insertCharacter(characterDB: CharacterDB)

    suspend fun getEpisodesOfCharacterDB(characterId: Int): List<EpisodeDB>

}