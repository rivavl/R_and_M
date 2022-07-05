package com.marina.rickandmorty.presentation.character.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marina.rickandmorty.domain.character.use_case.GetCharacterUseCase
import com.marina.rickandmorty.domain.episode.use_case.GetEpisodeUseCase
import com.marina.rickandmorty.domain.episode.use_case.GetEpisodesUseCase

class CharacterDetailViewModelFactory(
    private val id: Int,
    private val getCharacterUseCase: GetCharacterUseCase,
    private val getEpisodeUseCase: GetEpisodeUseCase,
    private val getEpisodesUseCase: GetEpisodesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterDetailViewModel::class.java)) {
            return CharacterDetailViewModel(
                id,
                getCharacterUseCase,
                getEpisodeUseCase,
                getEpisodesUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}