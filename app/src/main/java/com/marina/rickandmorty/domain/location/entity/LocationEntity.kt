package com.marina.rickandmorty.domain.location.entity

import com.marina.rickandmorty.data.location.entity.LocationDto
import com.marina.rickandmorty.presentation.location.entity.Location

data class LocationEntity(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
)

fun LocationDto.toLocationEntity(): LocationEntity {
    return LocationEntity(
        created = created,
        dimension = dimension,
        id = id,
        name = name,
        residents = residents,
        type = type,
        url = url
    )
}

fun LocationEntity.toLocation(): Location {
    val chars = residents.map { ch -> ch.split("/") }
    return Location(
        dimension = dimension,
        id = id,
        name = name,
        residents = chars.map { it[it.size - 1] },
        type = type
    )
}