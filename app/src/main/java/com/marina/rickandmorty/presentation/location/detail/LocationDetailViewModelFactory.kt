package com.marina.rickandmorty.presentation.location.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marina.rickandmorty.domain.character.use_case.GetCharacterUseCase
import com.marina.rickandmorty.domain.character.use_case.GetCharactersUseCase
import com.marina.rickandmorty.domain.location.use_case.GetLocationUseCase

class LocationDetailViewModelFactory(
    private val id: Int,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getCharacterUseCase: GetCharacterUseCase,
    private val getLocationUseCase: GetLocationUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationDetailViewModel::class.java)) {
            return LocationDetailViewModel(
                id,
                getCharactersUseCase,
                getCharacterUseCase,
                getLocationUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}