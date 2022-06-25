package com.marina.rickandmorty.domain.episode.use_case

import androidx.lifecycle.LiveData
import com.marina.rickandmorty.domain.episode.entity.EpisodeEntity
import com.marina.rickandmorty.domain.episode.repository.EpisodeRepository

class GetAllEpisodesUseCase(
    private val episodeRepository: EpisodeRepository
) {
    suspend fun getAllEpisodes(): LiveData<List<EpisodeEntity>> {
        return episodeRepository.getAllEpisodes()
    }
}