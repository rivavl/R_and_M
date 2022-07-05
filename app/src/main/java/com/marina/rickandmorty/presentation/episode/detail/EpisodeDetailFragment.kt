package com.marina.rickandmorty.presentation.episode.detail

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.marina.rickandmorty.R
import com.marina.rickandmorty.data.character.repository.CharacterRepositoryImpl
import com.marina.rickandmorty.data.episode.repository.EpisodeRepositoryImpl
import com.marina.rickandmorty.domain.character.use_case.GetCharactersUseCase
import com.marina.rickandmorty.domain.episode.use_case.GetEpisodeUseCase
import com.marina.rickandmorty.domain.episode.use_case.GetEpisodesUseCase
import com.marina.rickandmorty.domain.util.Resource
import com.marina.rickandmorty.presentation.episode.entity.Episode
import com.marina.rickandmorty.presentation.episode.list.recycler_view.CharacterAdapter


class EpisodeDetailFragment : Fragment(R.layout.fragment_episode_detail) {

    private var currentId: Int = -1

    private lateinit var episodeName: TextView
    private lateinit var episodeAirDate: TextView
    private lateinit var episodeCode: TextView

    private lateinit var charactersRecyclerView: RecyclerView
    private lateinit var charactersAdapter: CharacterAdapter

    private lateinit var viewModel: EpisodeDetailViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setupRecyclerView()
        parseParams()
        val repo = CharacterRepositoryImpl()
        val repoEp = EpisodeRepositoryImpl()
        val useCaseChar = GetCharactersUseCase(repo)
        val useCaseEp = GetEpisodeUseCase(repoEp)
        viewModel = ViewModelProvider(
            this,
            EpisodeDetailViewModelFactory(currentId, useCaseChar, useCaseEp)
        )[EpisodeDetailViewModel::class.java]

        viewModel.episode.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
//                    hideProgressBar()
//                    hideErrorMessage()
                    response.data?.let { setEpisodeInfo(it) }

                }
                is Resource.Loading -> {
//                    showProgressBar()
                }
                is Resource.Error -> {
//                    response.message?.let { msg -> showErrorMessage(msg) }
                }
            }
        }
        viewModel.charactersList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
//                    hideProgressBar()
//                    hideErrorMessage()
                    charactersAdapter.submitList(response.data)
                }
                is Resource.Loading -> {
//                    showProgressBar()
                }
                is Resource.Error -> {
//                    response.message?.let { msg -> showErrorMessage(msg) }
                }
            }
        }
    }

    private fun setEpisodeInfo(episode: Episode) {
        episodeName.text = episode.name
        episodeAirDate.text = episode.air_date
        episodeCode.text = episode.episode
    }

    private fun init() {
        episodeName = view?.findViewById(R.id.episode_detail_fragment_name)!!
        episodeAirDate = view?.findViewById(R.id.episode_detail_fragment_air_date)!!
        episodeCode = view?.findViewById(R.id.episode_detail_fragment_episode)!!
        charactersRecyclerView = view?.findViewById(R.id.episode_detail_fragment_characters_rv)!!
        setupRecyclerView()
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(EPISODE_ID)) {
            throw RuntimeException("param is absent")
        }
        currentId = args.getInt(EPISODE_ID)
    }

    private fun setupRecyclerView() {
        charactersAdapter = CharacterAdapter()
        charactersRecyclerView.apply {
            adapter = charactersAdapter
        }
    }

//    private fun hideProgressBar() {
//        progressBar.visibility = View.INVISIBLE
//        isLoading = false
//    }
//
//    private fun showProgressBar() {
//        progressBar.visibility = View.VISIBLE
//        isLoading = true
//    }
//
//    private fun hideErrorMessage() {
//        errorMessage.visibility = View.INVISIBLE
//        isError = false
//    }
//
//    private fun showErrorMessage(message: String) {
//        errorMessage.visibility = View.VISIBLE
//        errorMessage.text = message
//        isError = true
//    }

    companion object {
        private const val EPISODE_ID = "episode_id"

        fun getInstance(id: Int): EpisodeDetailFragment {
            return EpisodeDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(EPISODE_ID, id)
                }
            }
        }
    }
}