package com.marina.rickandmorty.presentation.episode.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marina.rickandmorty.domain.character.use_case.GetCharactersUseCase
import com.marina.rickandmorty.domain.episode.use_case.GetEpisodeUseCase
import com.marina.rickandmorty.domain.util.Resource
import com.marina.rickandmorty.presentation.character.entity.Character
import com.marina.rickandmorty.presentation.episode.entity.Episode
import kotlinx.coroutines.launch

class EpisodeDetailViewModel(
    private val id: Int,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getEpisodeUseCase: GetEpisodeUseCase
) : ViewModel() {

    private var _charactersList = MutableLiveData<Resource<MutableList<Character>>>()
    val charactersList: LiveData<Resource<MutableList<Character>>> get() = _charactersList

    private var _episode = MutableLiveData<Resource<Episode>>()
    val episode: LiveData<Resource<Episode>> get() = _episode


    init {
        viewModelScope.launch {
            getEpisode()
            getCharacters()
            println("99999999999999999999999999999999999999999")
            println(_charactersList.value?.data?.get(0)?.name)
        }
    }

    suspend fun getEpisode() {
        getEpisodeUseCase(id).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _episode.value = result.data?.let { Resource.Success(it) }
                }

                is Resource.Loading -> {
                    _episode.value = Resource.Loading()
                }

                is Resource.Error -> {
                    _episode.value = Resource.Error(result.message.toString())
                }
            }
        }
    }

    suspend fun getCharacters() {
        val chIds = _episode.value?.data?.characters
        val formatId = chIds.toString()
            .replace("[", "")
            .replace("]", "")
        val response = if (chIds?.size!! > 1) {
            getCharactersUseCase(formatId)
        } else {
            getEpisodeUseCase(formatId.toInt())
        }

        response.collect { result ->
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