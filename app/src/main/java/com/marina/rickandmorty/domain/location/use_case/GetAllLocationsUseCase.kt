package com.marina.rickandmorty.domain.location.use_case

import com.marina.rickandmorty.domain.location.entity.toLocation
import com.marina.rickandmorty.domain.location.entity.toLocationEntity
import com.marina.rickandmorty.domain.location.repository.LocationRepository
import com.marina.rickandmorty.domain.util.Resource
import com.marina.rickandmorty.presentation.location.entity.Location
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetAllLocationsUseCase(
    private val repository: LocationRepository
) {
    operator fun invoke(page: Int): Flow<Resource<List<Location>>> = flow {
        try {
            emit(Resource.Loading())
            val locations = repository.getAllLocations(page).map { it.toLocationEntity() }
            emit(Resource.Success(locations.map { it.toLocation() }))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}