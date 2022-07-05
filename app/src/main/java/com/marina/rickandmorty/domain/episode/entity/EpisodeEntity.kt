package com.marina.rickandmorty.domain.episode.entity

import com.marina.rickandmorty.data.episode.entity.EpisodeDto
import com.marina.rickandmorty.presentation.episode.entity.Episode

data class EpisodeEntity(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)

fun EpisodeDto.toEpisodeEntity(): EpisodeEntity {
    return EpisodeEntity(
        id = id,
        created = created,
        air_date = air_date,
        characters = characters,
        episode = episode,
        name = name,
        url = url
    )
}

fun EpisodeEntity.toEpisode(): Episode {
    val chars = characters.map { ch -> ch.split("/") }
    return Episode(
        id = id,
        air_date = air_date,
        characters = chars.map { it[it.size - 1] },
        episode = episode,
        name = name
    )
}