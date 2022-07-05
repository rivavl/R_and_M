package com.marina.rickandmorty.presentation.episode.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marina.rickandmorty.domain.episode.use_case.GetAllEpisodesUseCase
import com.marina.rickandmorty.domain.util.Resource
import com.marina.rickandmorty.presentation.episode.entity.Episode
import kotlinx.coroutines.launch

class EpisodeViewModel(
    private val getAllEpisodesUseCase: GetAllEpisodesUseCase
) : ViewModel() {

    private var _episodesList = MutableLiveData<Resource<MutableList<Episode>>>()

    val episodesList: LiveData<Resource<MutableList<Episode>>> get() = _episodesList

    private var page = 1

    init {
        getEpisodes()
    }

    fun getEpisodes() = viewModelScope.launch {
        getAllEpisodesUseCase(page++).collect { result ->
            when (result) {
                is Resource.Success -> {
                    val oldList = _episodesList.value?.data ?: mutableListOf()
                    val newList = result.data ?: mutableListOf()
                    oldList.addAll(newList)
                    _episodesList.value = Resource.Success(oldList)
                }

                is Resource.Loading -> {
                    _episodesList.value = Resource.Loading(_episodesList.value?.data)
                }

                is Resource.Error -> {
                    _episodesList.value = Resource.Error(
                        result.message.toString(),
                        _episodesList.value?.data
                    )
                }
            }
        }
    }
}