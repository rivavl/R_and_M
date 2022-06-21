package com.marina.rickandmorty.domain.episode.repository

import androidx.lifecycle.LiveData
import com.marina.rickandmorty.domain.episode.entity.Episode

interface EpisodeRepository {

    suspend fun getAllEpisodes(): LiveData<List<Episode>>

    suspend fun getEpisode(id: Int): Episode
}