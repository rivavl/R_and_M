package com.marina.rickandmorty.presentation.location.entity

data class Location(
    val id: Int,
    val dimension: String,
    val name: String,
    val residents: List<String>?,
    val type: String?
)
