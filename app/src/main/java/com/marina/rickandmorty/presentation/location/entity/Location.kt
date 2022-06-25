package com.marina.rickandmorty.presentation.location.entity

import com.marina.rickandmorty.presentation.character.entity.Character

data class Location(
    val id: Int,
    val dimension: String,
    val name: String,
    val residents: List<Character>,
    val type: String
)
