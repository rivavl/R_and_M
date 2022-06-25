package com.marina.rickandmorty.domain.location.use_case

import com.marina.rickandmorty.domain.location.entity.LocationEntity
import com.marina.rickandmorty.domain.location.repository.LocationRepository

class GetLocationUseCase(
    private val locationRepository: LocationRepository
) {
    suspend fun getLocation(id: Int): LocationEntity {
        return locationRepository.getLocation(id)
    }
}