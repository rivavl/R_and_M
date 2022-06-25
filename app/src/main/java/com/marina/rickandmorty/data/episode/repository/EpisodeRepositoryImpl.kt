package com.marina.rickandmorty.data.episode.repository

import androidx.lifecycle.LiveData
import com.marina.rickandmorty.domain.episode.entity.EpisodeEntity
import com.marina.rickandmorty.domain.episode.repository.EpisodeRepository

class EpisodeRepositoryImpl : EpisodeRepository {
    override suspend fun getAllEpisodes(): LiveData<List<EpisodeEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getEpisode(id: Int): EpisodeEntity {
        TODO("Not yet implemented")
    }
}