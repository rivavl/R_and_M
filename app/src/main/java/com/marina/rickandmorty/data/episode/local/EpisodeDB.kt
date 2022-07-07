package com.marina.rickandmorty.data.episode.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episodes")
data class EpisodeDB(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val air_date: String?,
    val created: String?,
    val episode: String?,
    val name: String?
)