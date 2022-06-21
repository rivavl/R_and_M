package com.marina.rickandmorty.domain.location.use_case

import com.marina.rickandmorty.domain.location.entity.Location
import com.marina.rickandmorty.domain.location.repository.LocationRepository

class GetLocationUseCase(
    private val locationRepository: LocationRepository
) {
    suspend fun getLocation(id: Int): Location {
        return locationRepository.getLocation(id)
    }
}