package com.marina.rickandmorty.presentation.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marina.rickandmorty.domain.episode.use_case.GetAllEpisodesUseCase
import com.marina.rickandmorty.domain.location.use_case.GetAllLocationsUseCase
import com.marina.rickandmorty.presentation.episode.list.EpisodeViewModel
import com.marina.rickandmorty.presentation.location.list.LocationViewModel

class LocationViewModelFactory(
    private val useCase: GetAllLocationsUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationViewModel::class.java)) {
            return LocationViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}