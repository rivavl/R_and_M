package com.marina.rickandmorty.domain.episode.repository

import androidx.lifecycle.LiveData
import com.marina.rickandmorty.domain.episode.entity.EpisodeEntity

interface EpisodeRepository {

    suspend fun getAllEpisodes(): LiveData<List<EpisodeEntity>>

    suspend fun getEpisode(id: Int): EpisodeEntity
}