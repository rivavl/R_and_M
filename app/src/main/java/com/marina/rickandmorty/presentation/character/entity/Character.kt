package com.marina.rickandmorty.presentation.character.entity

data class Character(
    val id: Int,
    val episode: List<String>?,
    val gender: String,
    val image: String,
    val locationName: String?,
    val locationId: Int?,
    val name: String,
    val originName: String?,
    val originId: Int?,
    val species: String,
    val status: String,
    val type: String?
)