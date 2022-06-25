package com.marina.rickandmorty.data.location.repository

import androidx.lifecycle.LiveData
import com.marina.rickandmorty.domain.location.entity.LocationEntity
import com.marina.rickandmorty.domain.location.repository.LocationRepository

class LocationRepositoryImpl : LocationRepository {
    override suspend fun getAllLocations(): LiveData<List<LocationEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getLocation(id: Int): LocationEntity {
        TODO("Not yet implemented")
    }
}