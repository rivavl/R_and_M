package com.marina.rickandmorty.presentation.location.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marina.rickandmorty.domain.character.use_case.GetCharacterUseCase
import com.marina.rickandmorty.domain.character.use_case.GetCharactersUseCase
import com.marina.rickandmorty.domain.location.use_case.GetLocationUseCase
import com.marina.rickandmorty.domain.util.Resource
import com.marina.rickandmorty.presentation.character.entity.Character
import com.marina.rickandmorty.presentation.location.entity.Location
import kotlinx.coroutines.launch

class LocationDetailViewModel(
    private val id: Int,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getCharacterUseCase: GetCharacterUseCase,
    private val getLocationUseCase: GetLocationUseCase
) : ViewModel() {

    private var _charactersList = MutableLiveData<Resource<MutableList<Character>>>()
    val charactersList: LiveData<Resource<MutableList<Character>>> get() = _charactersList

    private var _location = MutableLiveData<Resource<Location>>()
    val location: LiveData<Resource<Location>> get() = _location


    init {
        viewModelScope.launch {
            getLocation()
            getCharacters()
        }
    }

    suspend fun getLocation() {
        getLocationUseCase(id).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _location.value = result.data?.let { Resource.Success(it[0]) }
                }

                is Resource.Loading -> {
                    _location.value = Resource.Loading()
                }

                is Resource.Error -> {
                    _location.value = Resource.Error(result.message.toString())
                }
            }
        }
    }

    suspend fun getCharacters() {

        val chIds = _location.value?.data?.residents
        val formatId = chIds.toString()
            .replace("[", "")
            .replace("]", "")

        getCharactersUseCase(formatId)
        val response = if (chIds?.size!! > 1) {
            getCharactersUseCase(formatId)
        } else if(chIds.isEmpty()) {
            null
        } else {
            getCharacterUseCase(formatId.toInt())
        }

        response?.collect { result ->
            when (result) {
                is Resource.Success -> {
                    _charactersList.value =
                        result.data?.let { Resource.Success(it as MutableList<Character>) }!!
                }

                is Resource.Loading -> {
                    _charactersList.value = Resource.Loading()
                }

                is Resource.Error -> {
                    _charactersList.value = Resource.Error(result.message.toString())
                }
            }
        }
    }
}