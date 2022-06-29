package com.marina.rickandmorty.domain.location.use_case

import com.marina.rickandmorty.Resource
import com.marina.rickandmorty.domain.location.entity.LocationEntity
import com.marina.rickandmorty.domain.location.entity.toLocationEntity
import com.marina.rickandmorty.domain.location.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetAllLocationsUseCase(
    private val repository: LocationRepository
) {
    operator fun invoke(page: Int): Flow<Resource<List<LocationEntity>>> = flow {
        try {
            emit(Resource.Loading())
            val locations = repository.getAllLocations(page).map { it.toLocationEntity() }
            emit(Resource.Success(locations))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}