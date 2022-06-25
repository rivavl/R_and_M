package com.marina.rickandmorty.domain.character.use_case

import com.marina.rickandmorty.domain.character.entity.CharacterEntity
import com.marina.rickandmorty.domain.character.repository.CharacterRepository

class GetCharacterUseCase(
    private val characterRepository: CharacterRepository
) {
    suspend fun getCharacter(id: Int): CharacterEntity {
        return characterRepository.getCharacter(id)
    }
}