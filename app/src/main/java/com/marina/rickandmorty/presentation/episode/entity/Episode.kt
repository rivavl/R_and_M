package com.marina.rickandmorty.presentation.episode.entity

data class Episode(
    val id: Int,
    val air_date: String,
    val characters: List<String>?,
    val episode: String,
    val name: String,
)
