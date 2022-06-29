package com.marina.rickandmorty.domain.episode.repository

import com.marina.rickandmorty.data.episode.entity.EpisodeDto

interface EpisodeRepository {

    suspend fun getAllEpisodes(page: Int): List<EpisodeDto>

    suspend fun getEpisode(id: Int): EpisodeDto
}