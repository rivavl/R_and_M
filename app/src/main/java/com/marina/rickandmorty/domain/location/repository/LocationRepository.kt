package com.marina.rickandmorty.domain.location.repository

import com.marina.rickandmorty.data.location.entity.LocationDto

interface LocationRepository {

    suspend fun getAllLocations(page: Int): List<LocationDto>

    suspend fun getLocation(id: Int): LocationDto

    suspend fun getLocations(ids: String): List<LocationDto>
}