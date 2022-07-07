package com.marina.rickandmorty.presentation.character.detail

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marina.rickandmorty.data.util.database.DatabaseRM
import com.marina.rickandmorty.domain.character.use_case.GetCharacterUseCase
import com.marina.rickandmorty.domain.episode.use_case.GetEpisodeUseCase
import com.marina.rickandmorty.domain.episode.use_case.GetEpisodesUseCase
import com.marina.rickandmorty.domain.util.Resource
import com.marina.rickandmorty.presentation.character.entity.Character
import com.marina.rickandmorty.presentation.episode.entity.Episode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val id: Int,
    private val getCharacterUseCase: GetCharacterUseCase,
    private val getEpisodeUseCase: GetEpisodeUseCase,
    private val getEpisodesUseCase: GetEpisodesUseCase
) : ViewModel() {

    private var _episodesList = MutableLiveData<Resource<MutableList<Episode>>>()
    val episodesList: LiveData<Resource<MutableList<Episode>>> get() = _episodesList

    private var _character = MutableLiveData<Resource<Character>>()
    val character: LiveData<Resource<Character>> get() = _character


    init {
        viewModelScope.launch {
            getCharacter()
            getEpisodes()
        }
    }

    suspend fun getCharacter() {
        getCharacterUseCase(id).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _character.value = result.data?.let { Resource.Success(it[0]) }
                }

                is Resource.Loading -> {
                    _character.value = Resource.Loading()
                }

                is Resource.Error -> {
                    _character.value = Resource.Error(result.message.toString())
                }
            }
        }
    }

    suspend fun getEpisodes() {
        val epIds = _character.value?.data?.episode
        val formatId = epIds.toString()
            .replace("[", "")
            .replace("]", "")
        val response = if (epIds?.size!! > 1) {
            getEpisodesUseCase(formatId)
        } else {
            getEpisodeUseCase(formatId.toInt())
        }

        response.collect { result ->
            when (result) {
                is Resource.Success -> {
                    _episodesList.value =
                        result.data?.let { Resource.Success(it as MutableList<Episode>) }!!
                }

                is Resource.Loading -> {
                    _episodesList.value = Resource.Loading()
                }

                is Resource.Error -> {
                    _episodesList.value = Resource.Error(result.message.toString())
                }
            }
        }


    }

    private suspend fun processResponse(
        response: Flow<Resource<MutableList<Episode>>>
    ) {
        response.collect { result ->
            when (result) {
                is Resource.Success -> {
                    _episodesList.value =
                        result.data?.let { Resource.Success(it) }!!
                }

                is Resource.Loading -> {
                    _episodesList.value = Resource.Loading()
                }

                is Resource.Error -> {
                    _episodesList.value = Resource.Error(result.message.toString())
                }
            }
        }
    }
}