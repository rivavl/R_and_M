package com.marina.rickandmorty.domain.location.use_case

import androidx.lifecycle.LiveData
import com.marina.rickandmorty.domain.location.entity.Location
import com.marina.rickandmorty.domain.location.repository.LocationRepository

class GetAllLocationsUseCase(
    private val locationRepository: LocationRepository
) {
    suspend fun getAllLocations(): LiveData<List<Location>> {
        return locationRepository.getAllLocations()
    }
}