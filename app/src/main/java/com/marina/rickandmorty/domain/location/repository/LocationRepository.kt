package com.marina.rickandmorty.domain.location.repository

import androidx.lifecycle.LiveData
import com.marina.rickandmorty.domain.location.entity.LocationEntity

interface LocationRepository {

    suspend fun getAllLocations(): LiveData<List<LocationEntity>>

    suspend fun getLocation(id: Int): LocationEntity
}