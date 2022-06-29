package com.marina.rickandmorty.data.character.repository

import com.marina.rickandmorty.data.character.entity.CharacterDto
import com.marina.rickandmorty.data.util.RetrofitInstance
import com.marina.rickandmorty.domain.character.repository.CharacterRepository

class CharacterRepositoryImpl() : CharacterRepository {

    override suspend fun getAllCharacters(pageNumber: Int): List<CharacterDto> {
        return RetrofitInstance.characterApi.getCharacters(pageNumber).characterDtos
    }

    override suspend fun getCharacter(id: Int): CharacterDto {
        return RetrofitInstance.characterApi.getCharacterById(id)
    }
}