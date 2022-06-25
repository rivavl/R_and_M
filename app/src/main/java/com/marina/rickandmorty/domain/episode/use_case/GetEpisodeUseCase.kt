package com.marina.rickandmorty.domain.episode.use_case

import com.marina.rickandmorty.domain.episode.entity.EpisodeEntity
import com.marina.rickandmorty.domain.episode.repository.EpisodeRepository

class GetEpisodeUseCase(
    private val episodeRepository: EpisodeRepository
) {
    suspend fun getEpisode(id: Int): EpisodeEntity {
        return episodeRepository.getEpisode(id)
    }
}