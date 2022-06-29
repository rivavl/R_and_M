package com.marina.rickandmorty.data.episode.repository

import com.marina.rickandmorty.data.episode.entity.EpisodeDto
import com.marina.rickandmorty.data.util.RetrofitInstance
import com.marina.rickandmorty.domain.episode.repository.EpisodeRepository

class EpisodeRepositoryImpl() : EpisodeRepository {

    override suspend fun getAllEpisodes(page: Int): List<EpisodeDto> {
        return RetrofitInstance.episodeApi.getEpisodes(page).episodeDtos
    }

    override suspend fun getEpisode(id: Int): EpisodeDto {
        return RetrofitInstance.episodeApi.getEpisodeById(id)
    }
}