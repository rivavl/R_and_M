package com.marina.rickandmorty.domain.location.entity

import com.marina.rickandmorty.domain.character.entity.Character

data class Location(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<Character>,
    val type: String,
    val url: String
)