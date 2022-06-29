package com.marina.rickandmorty.domain.episode.use_case

import androidx.lifecycle.LiveData
import com.marina.rickandmorty.Resource
import com.marina.rickandmorty.domain.character.entity.CharacterEntity
import com.marina.rickandmorty.domain.character.entity.toCharacterEntity
import com.marina.rickandmorty.domain.episode.entity.EpisodeEntity
import com.marina.rickandmorty.domain.episode.entity.toEpisodeEntity
import com.marina.rickandmorty.domain.episode.repository.EpisodeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetAllEpisodesUseCase(
    private val repository: EpisodeRepository
) {

    operator fun invoke(page: Int): Flow<Resource<List<EpisodeEntity>>> = flow {
        try {
            emit(Resource.Loading())
            val characters = repository.getAllEpisodes(page).map { it.toEpisodeEntity()}
            emit(Resource.Success(characters))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}