package com.marina.rickandmorty.domain.episode.use_case

import com.marina.rickandmorty.domain.episode.entity.toEpisode
import com.marina.rickandmorty.domain.episode.entity.toEpisodeEntity
import com.marina.rickandmorty.domain.episode.repository.EpisodeRepository
import com.marina.rickandmorty.domain.util.Resource
import com.marina.rickandmorty.presentation.episode.entity.Episode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetEpisodeUseCase(
    private val repository: EpisodeRepository
) {

    operator fun invoke(id: Int): Flow<Resource<Episode>> = flow {
        try {
            emit(Resource.Loading())
            val episodes = repository.getEpisode(id).toEpisodeEntity()
            emit(Resource.Success(episodes.toEpisode()))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}