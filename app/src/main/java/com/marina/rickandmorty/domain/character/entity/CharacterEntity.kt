package com.marina.rickandmorty.domain.character.entity

import com.marina.rickandmorty.domain.episode.entity.EpisodeEntity

data class CharacterEntity(
    val created: String,
    val episode: List<EpisodeEntity>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: CharLocation,
    val name: String,
    val origin: CharLocation,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)