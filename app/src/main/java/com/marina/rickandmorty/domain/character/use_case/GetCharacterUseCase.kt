package com.marina.rickandmorty.domain.character.use_case

import com.marina.rickandmorty.domain.character.entity.toCharacter
import com.marina.rickandmorty.domain.character.entity.toCharacterEntity
import com.marina.rickandmorty.domain.character.repository.CharacterRepository
import com.marina.rickandmorty.domain.util.Resource
import com.marina.rickandmorty.presentation.character.entity.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCharacterUseCase(
    private val repository: CharacterRepository
) {
    operator fun invoke(id: Int): Flow<Resource<List<Character>>> = flow {
        try {
            emit(Resource.Loading())
            val character = repository.getCharacter(id).map { it.toCharacterEntity() }
            emit(Resource.Success(character.map { it.toCharacter() }))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}