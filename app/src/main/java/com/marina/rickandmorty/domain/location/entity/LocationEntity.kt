package com.marina.rickandmorty.domain.location.entity

import com.marina.rickandmorty.domain.character.entity.CharacterEntity

data class LocationEntity(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<CharacterEntity>,
    val type: String,
    val url: String
)