package com.marina.rickandmorty.presentation.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marina.rickandmorty.domain.character.use_case.GetAllCharactersUseCase
import com.marina.rickandmorty.presentation.character.list.CharacterViewModel

class CharacterViewModelFactory(
    private val useCase: GetAllCharactersUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            return CharacterViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}