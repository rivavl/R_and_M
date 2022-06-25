package com.marina.rickandmorty.presentation.episode.entity

import com.marina.rickandmorty.presentation.character.entity.Character

data class Episode(
    val id: Int,
    val air_date: String,
    val characters: List<Character>,
    val episode: String,
    val name: String,
)
