package com.marina.rickandmorty.presentation.character

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marina.rickandmorty.domain.character.use_case.GetAllCharactersUseCase
import com.marina.rickandmorty.presentation.character.list.CharacterViewModel

class CharacterViewModelFactory(
    private val useCase: GetAllCharactersUseCase,
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            return CharacterViewModel(useCase, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}