package com.marina.rickandmorty.data.location.remote

import com.marina.rickandmorty.data.location.entity.LocationDto
import com.marina.rickandmorty.data.location.entity.LocationResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationAPI {

    @GET("location/")
    suspend fun getLocations(
        @Query("page")
        pageNumber: Int = 1
    ): LocationResponse

    @GET("location/{id}")
    suspend fun getLocationById(
        @Path("id") id: Int
    ): LocationDto
}