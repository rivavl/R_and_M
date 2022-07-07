package com.marina.rickandmorty.domain.location.entity

import com.marina.rickandmorty.data.location.entity.LocationDto
import com.marina.rickandmorty.presentation.location.entity.Location

data class LocationEntity(
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String
)

fun LocationDto.toLocationEntity(): LocationEntity {
    return LocationEntity(
        dimension = dimension,
        id = id,
        name = name,
        residents = residents,
        type = type
    )
}

fun LocationEntity.toLocation(): Location {
    val chars = residents.map { ch -> ch.split("/") }
    return Location(
        dimension = dimension,
        id = id,
        name = name,
        residents = chars.map { it.getOrNull(it.size - 1) },
        type = type
    )
}