package com.marina.rickandmorty.domain.character.use_case

import com.marina.rickandmorty.Resource
import com.marina.rickandmorty.domain.character.entity.CharacterEntity
import com.marina.rickandmorty.domain.character.entity.toCharacterEntity
import com.marina.rickandmorty.domain.character.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetAllCharactersUseCase(
    private val repository: CharacterRepository
) {

    operator fun invoke(page: Int): Flow<Resource<List<CharacterEntity>>> = flow {
        try {
            emit(Resource.Loading())
            val characters = repository.getAllCharacters(page).map { it.toCharacterEntity() }
            emit(Resource.Success(characters))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

}