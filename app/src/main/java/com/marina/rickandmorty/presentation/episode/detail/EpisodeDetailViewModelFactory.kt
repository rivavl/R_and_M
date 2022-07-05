package com.marina.rickandmorty.presentation.episode.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marina.rickandmorty.domain.character.use_case.GetCharactersUseCase
import com.marina.rickandmorty.domain.episode.use_case.GetEpisodeUseCase

class EpisodeDetailViewModelFactory(
    private val id: Int,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getEpisodeUseCase: GetEpisodeUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EpisodeDetailViewModel::class.java)) {
            return EpisodeDetailViewModel(
                id,
                getCharactersUseCase,
                getEpisodeUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}