package com.marina.rickandmorty.data.episode.local

import androidx.room.Dao
import androidx.room.Query

@Dao
interface EpisodeDao {

    @Query("select * from episodes where id>=:page and id<(:page+20)")
    suspend fun getEpisodesPage(page: Int): List<EpisodeDB>

    @Query("select * from episodes where id=:id")
    suspend fun getEpisode(id: Int): EpisodeDB
}