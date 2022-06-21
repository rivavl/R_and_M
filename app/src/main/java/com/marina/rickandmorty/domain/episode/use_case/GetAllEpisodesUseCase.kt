package com.marina.rickandmorty.domain.episode.use_case

import androidx.lifecycle.LiveData
import com.marina.rickandmorty.domain.episode.entity.Episode
import com.marina.rickandmorty.domain.episode.repository.EpisodeRepository

class GetAllEpisodesUseCase(
    private val episodeRepository: EpisodeRepository
) {
    suspend fun getAllEpisodes(): LiveData<List<Episode>> {
        return episodeRepository.getAllEpisodes()
    }
}