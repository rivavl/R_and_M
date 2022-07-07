package com.marina.rickandmorty.domain.character.entity

import com.marina.rickandmorty.data.character.local.entity.CharacterDB
import com.marina.rickandmorty.data.character.remote.entity.CharacterDto
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

fun CharacterDB.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        created = created,
        episode = episode,
        gender = gender,
        image = image,
        locationName = locationName,
        locationUrl = locationUrl,
        name = name,
        originName = originName,
        originUrl = originUrl,
        species = species,
        status = status,
        type = type
    )
}

fun CharacterEntity.toCharacter(): Character {
    val episodesIds = episode.map { ep -> ep.split("/") }
    val locationUrlSplit = locationUrl.split("/")

    val locationId = if (locationUrlSplit.size > 1) {
        locationUrlSplit[locationUrlSplit.size - 1].toInt()
    } else {
        -1
    }

    val originUrlSplit = locationUrl.split("/")

    val originId = if (originUrlSplit.size > 1) {
        originUrlSplit[originUrlSplit.size - 1].toInt()
    } else {
        -1
    }
    return Character(
        id = id,
        episode = episodesIds.map { it[it.size - 1] },
        gender = gender,
        image = image,
        name = name,
        species = species,
        status = status,
        type = type,
        locationId = locationId,
        locationName = locationName,
        originId = originId,
        originName = originName
    )
}