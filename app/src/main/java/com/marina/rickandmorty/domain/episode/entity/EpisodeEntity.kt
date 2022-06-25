package com.marina.rickandmorty.domain.episode.entity

import com.marina.rickandmorty.domain.character.entity.CharacterEntity

data class EpisodeEntity(
    val air_date: String,
    val characters: List<CharacterEntity>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)