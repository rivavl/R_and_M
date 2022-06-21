package com.marina.rickandmorty.domain.character.repository

import androidx.lifecycle.LiveData
import com.marina.rickandmorty.domain.character.entity.Character

interface CharacterRepository {

    suspend fun getAllCharacters(): LiveData<List<Character>>

    suspend fun getCharacter(id: Int): Character
}