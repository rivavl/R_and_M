package com.marina.rickandmorty.domain.character.repository

import androidx.lifecycle.LiveData
import com.marina.rickandmorty.domain.character.entity.CharacterEntity

interface CharacterRepository {

    suspend fun getAllCharacters(): LiveData<List<CharacterEntity>>

    suspend fun getCharacter(id: Int): CharacterEntity
}