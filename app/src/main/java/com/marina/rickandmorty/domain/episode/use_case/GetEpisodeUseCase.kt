package com.marina.rickandmorty.domain.episode.use_case

import com.marina.rickandmorty.Resource
import com.marina.rickandmorty.domain.episode.entity.EpisodeEntity
import com.marina.rickandmorty.domain.episode.entity.toEpisodeEntity
import com.marina.rickandmorty.domain.episode.repository.EpisodeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetEpisodeUseCase(
    private val repository: EpisodeRepository
) {

    operator fun invoke(id: Int): Flow<Resource<EpisodeEntity>> = flow {
        try {
            emit(Resource.Loading())
            val episode = repository.getEpisode(id).toEpisodeEntity()
            emit(Resource.Success(episode))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}