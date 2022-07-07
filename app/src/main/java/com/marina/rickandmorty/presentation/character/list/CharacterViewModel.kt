package com.marina.rickandmorty.presentation.character.list

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marina.rickandmorty.data.character.local.entity.CharacterDB
import com.marina.rickandmorty.data.util.database.DatabaseRM
import com.marina.rickandmorty.domain.character.use_case.GetAllCharactersUseCase
import com.marina.rickandmorty.domain.util.Resource
import com.marina.rickandmorty.presentation.character.entity.Character
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    val context: Context
) : ViewModel() {

    private var _charactersList = MutableLiveData<Resource<MutableList<Character>>>()

    val charactersList: LiveData<Resource<MutableList<Character>>> get() = _charactersList

    private var page = 1

    init {
        getCharacters()
    }

    fun getCharacters() = viewModelScope.launch {
        getAllCharactersUseCase(page++).collect { result ->
            when (result) {
                is Resource.Success -> {
                    val oldList = _charactersList.value?.data ?: mutableListOf()
                    val newList = result.data ?: mutableListOf()
                    oldList.addAll(newList)
                    _charactersList.value = Resource.Success(oldList)
                }

                is Resource.Loading -> {
                    _charactersList.value = Resource.Loading(_charactersList.value?.data)
                }

                is Resource.Error -> {
                    _charactersList.value =
                        Resource.Error(result.message.toString(), _charactersList.value?.data)
                }
            }
        }
    }
}