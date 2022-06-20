package com.marina.rickandmorty.domain.entity.location

import com.marina.rickandmorty.domain.entity.Info

data class LocationsList(
    val info: Info,
    val results: List<Location>
)