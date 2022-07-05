package com.marina.rickandmorty.presentation.location.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marina.rickandmorty.domain.location.use_case.GetAllLocationsUseCase
import com.marina.rickandmorty.domain.util.Resource
import com.marina.rickandmorty.presentation.episode.entity.Episode
import com.marina.rickandmorty.presentation.location.entity.Location
import kotlinx.coroutines.launch

class LocationViewModel(
    private val getAllLocationsUseCase: GetAllLocationsUseCase
) : ViewModel() {

    private var _locationsList = MutableLiveData<Resource<MutableList<Location>>>()

    val locationsList: LiveData<Resource<MutableList<Location>>> get() = _locationsList

    private var page = 1

    init {
        getEpisodes()
    }

    fun getEpisodes() = viewModelScope.launch {
        getAllLocationsUseCase(page++).collect { result ->
            when (result) {
                is Resource.Success -> {
                    val oldList = _locationsList.value?.data ?: mutableListOf()
                    val newList = result.data ?: mutableListOf()
                    oldList.addAll(newList)
                    _locationsList.value = Resource.Success(oldList)
                }

                is Resource.Loading -> {
                    _locationsList.value = Resource.Loading(_locationsList.value?.data)
                }

                is Resource.Error -> {
                    _locationsList.value = Resource.Error(
                        result.message.toString(),
                        _locationsList.value?.data
                    )
                }
            }
        }
    }
}