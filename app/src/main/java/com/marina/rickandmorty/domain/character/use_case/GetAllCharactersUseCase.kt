package com.marina.rickandmorty.domain.character.use_case

import androidx.lifecycle.LiveData
import com.marina.rickandmorty.domain.character.entity.Character
import com.marina.rickandmorty.domain.character.repository.CharacterRepository

class GetAllCharactersUseCase(
    private val characterRepository: CharacterRepository
) {
    suspend fun getAllCharacters(): LiveData<List<Character>> {
        return characterRepository.getAllCharacters()
    }

}