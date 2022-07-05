package com.marina.rickandmorty.presentation.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marina.rickandmorty.domain.episode.use_case.GetAllEpisodesUseCase
import com.marina.rickandmorty.presentation.episode.list.EpisodeViewModel

class EpisodeViewModelFactory(
    private val useCase: GetAllEpisodesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EpisodeViewModel::class.java)) {
            return EpisodeViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}