package com.marina.rickandmorty.data.location.remote.entity

import com.google.gson.annotations.SerializedName

data class LocationResponse(
    val info: Info,
    @SerializedName("results")
    val locationDtos: List<LocationDto>
)