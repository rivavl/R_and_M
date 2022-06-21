package com.marina.rickandmorty.domain.location.repository

import androidx.lifecycle.LiveData
import com.marina.rickandmorty.domain.location.entity.Location

interface LocationRepository {

    suspend fun getAllLocations(): LiveData<List<Location>>

    suspend fun getLocation(id: Int): Location
}