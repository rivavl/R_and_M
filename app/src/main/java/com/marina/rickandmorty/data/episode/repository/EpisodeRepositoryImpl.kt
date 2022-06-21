package com.marina.rickandmorty.data.episode.repository

import androidx.lifecycle.LiveData
import com.marina.rickandmorty.domain.episode.entity.Episode
import com.marina.rickandmorty.domain.episode.repository.EpisodeRepository

class EpisodeRepositoryImpl : EpisodeRepository {
    override suspend fun getAllEpisodes(): LiveData<List<Episode>> {
        TODO("Not yet implemented")
    }

    override suspend fun getEpisode(id: Int): Episode {
        TODO("Not yet implemented")
    }
}