package com.marina.rickandmorty.data.location.entity

import com.google.gson.annotations.SerializedName

data class LocationResponse(
    val info: Info,
    @SerializedName("results")
    val locationDtos: List<LocationDto>
)