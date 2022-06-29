package com.marina.rickandmorty.data.location.repository

import com.marina.rickandmorty.data.location.entity.LocationDto
import com.marina.rickandmorty.data.util.RetrofitInstance
import com.marina.rickandmorty.domain.location.repository.LocationRepository

class LocationRepositoryImpl : LocationRepository {

    override suspend fun getAllLocations(page: Int): List<LocationDto> {
        return RetrofitInstance.locationApi.getLocations(page).locationDtos
    }

    override suspend fun getLocation(id: Int): LocationDto {
        return RetrofitInstance.locationApi.getLocationById(id)
    }
}