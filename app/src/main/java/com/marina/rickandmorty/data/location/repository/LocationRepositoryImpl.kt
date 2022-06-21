package com.marina.rickandmorty.data.location.repository

import androidx.lifecycle.LiveData
import com.marina.rickandmorty.domain.location.entity.Location
import com.marina.rickandmorty.domain.location.repository.LocationRepository

class LocationRepositoryImpl : LocationRepository {
    override suspend fun getAllLocations(): LiveData<List<Location>> {
        TODO("Not yet implemented")
    }

    override suspend fun getLocation(id: Int): Location {
        TODO("Not yet implemented")
    }
}