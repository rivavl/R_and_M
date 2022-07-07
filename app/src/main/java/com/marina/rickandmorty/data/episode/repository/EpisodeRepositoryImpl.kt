package com.marina.rickandmorty.data.episode.repository

import com.marina.rickandmorty.data.episode.remote.entity.EpisodeDto
import com.marina.rickandmorty.data.util.network.RetrofitInstance
import com.marina.rickandmorty.domain.episode.repository.EpisodeRepository

class EpisodeRepositoryImpl : EpisodeRepository {

    override suspend fun getAllEpisodes(page: Int): List<EpisodeDto> {
        return RetrofitInstance.episodeApi.getEpisodes(page).episodeDtos
    }

    override suspend fun getEpisode(id: Int): List<EpisodeDto> {
        return listOf(RetrofitInstance.episodeApi.getEpisodeById(id))
    }

    override suspend fun getEpisodes(ids: String): List<EpisodeDto> {
        return RetrofitInstance.episodeApi.getEpisodesByIds(ids)
    }
}