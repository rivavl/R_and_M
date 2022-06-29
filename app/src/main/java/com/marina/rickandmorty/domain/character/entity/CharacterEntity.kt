package com.marina.rickandmorty.domain.character.entity

import com.marina.rickandmorty.data.character.entity.CharacterDto
import com.marina.rickandmorty.presentation.character.entity.Character

data class CharacterEntity(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val locationName: String,
    val locationUrl: String,
    val name: String,
    val originName: String,
    val originUrl: String,
    val species: String,
    val status: String,
    val type: String
)

fun CharacterDto.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        created = created,
        episode = episode,
        gender = gender,
        id = id,
        image = image,
        locationName = location.name,
        locationUrl = location.url,
        name = name,
        originName = origin.name,
        originUrl = origin.url,
        species = species,
        status = status,
        type = type
    )
}

fun CharacterDto.toCharacter(): Character {
    return Character(
        id = id,
        episode = episode,
        gender = gender,
        image = image,
        name = name,
        species = species,
        status = status,
        type = type
    )
}