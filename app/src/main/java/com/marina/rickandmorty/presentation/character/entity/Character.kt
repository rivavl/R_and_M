package com.marina.rickandmorty.presentation.character.entity

import com.marina.rickandmorty.presentation.episode.entity.Episode

data class Character(
    val id: Int,
    val episode: List<Episode>,
    val gender: String,
    val image: String,
    val location: CLocation,
    val name: String,
    val origin: CLocation,
    val species: String,
    val status: String,
    val type: String
)