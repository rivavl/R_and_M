package com.marina.rickandmorty.domain.character.entity

data class Character(
    val created: String,
    val episode: List<Any>,
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