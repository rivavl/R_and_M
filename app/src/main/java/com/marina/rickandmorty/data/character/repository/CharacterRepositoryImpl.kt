package com.marina.rickandmorty.data.character.repository

import androidx.lifecycle.LiveData
import com.marina.rickandmorty.domain.character.entity.CharacterEntity
import com.marina.rickandmorty.domain.character.repository.CharacterRepository

class CharacterRepositoryImpl : CharacterRepository {
    override suspend fun getAllCharacters(): LiveData<List<CharacterEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacter(id: Int): CharacterEntity {
        TODO("Not yet implemented")
    }
}