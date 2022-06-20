package com.marina.rickandmorty.domain.entity.location

import com.marina.rickandmorty.domain.entity.character.Character

data class Location(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<Character>,
    val type: String,
    val url: String
)