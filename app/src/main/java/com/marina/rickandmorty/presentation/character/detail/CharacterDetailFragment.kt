package com.marina.rickandmorty.presentation.character.detail

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marina.rickandmorty.R
import com.marina.rickandmorty.data.character.repository.CharacterRepositoryImpl
import com.marina.rickandmorty.data.episode.repository.EpisodeRepositoryImpl
import com.marina.rickandmorty.domain.character.use_case.GetCharacterUseCase
import com.marina.rickandmorty.domain.episode.use_case.GetEpisodeUseCase
import com.marina.rickandmorty.domain.episode.use_case.GetEpisodesUseCase
import com.marina.rickandmorty.domain.util.Resource
import com.marina.rickandmorty.presentation.character.entity.Character
import com.marina.rickandmorty.presentation.episode.list.recycler_view.EpisodeListAdapter


class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {

    private var currentId: Int = -1

    private lateinit var characterName: TextView
    private lateinit var characterStatus: TextView
    private lateinit var characterSpecies: TextView
    private lateinit var characterType: TextView
    private lateinit var characterGender: TextView
    private lateinit var characterOrigin: TextView
    private lateinit var characterImage: ImageView

    private lateinit var episodesRecyclerView: RecyclerView
    private lateinit var episodeAdapter: EpisodeListAdapter

    private lateinit var viewModel: CharacterDetailViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setupRecyclerView()
        parseParams()
        val repo = CharacterRepositoryImpl()
        val repoEp = EpisodeRepositoryImpl()
        val useCaseChar = GetCharacterUseCase(repo)
        val useCaseEp = GetEpisodeUseCase(repoEp)
        val useCaseEps = GetEpisodesUseCase(repoEp)
        viewModel = ViewModelProvider(
            this,
            CharacterDetailViewModelFactory(currentId, useCaseChar, useCaseEp, useCaseEps)
        )[CharacterDetailViewModel::class.java]

        viewModel.character.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
//                    hideProgressBar()
//                    hideErrorMessage()
                    response.data?.let { setCharacterInfo(it) }

                }
                is Resource.Loading -> {
//                    showProgressBar()
                }
                is Resource.Error -> {
//                    response.message?.let { msg -> showErrorMessage(msg) }
                }
            }
        }
        viewModel.episodesList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
//                    hideProgressBar()
//                    hideErrorMessage()
                    println("1111111111111111111111111111")
                    println(response.data)
                    episodeAdapter.submitList(response.data)
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

    private fun setCharacterInfo(character: Character) {
        characterName.text = character.name
        characterStatus.text = character.status
        characterSpecies.text = character.species
        characterType.text = character.type
        characterGender.text = character.gender
        characterOrigin.text = character.originName
        loadImage(character.image, characterImage, requireContext())
    }

    private fun loadImage(url: String, imageView: ImageView, context: Context) {
        Glide.with(context)
            .load(url)
            .into(imageView)
    }


    private fun init() {
        characterName = view?.findViewById(R.id.char_detail_fragment_name)!!
        characterStatus = view?.findViewById(R.id.char_detail_fragment_status)!!
        characterSpecies = view?.findViewById(R.id.char_detail_fragment_species)!!
        characterType = view?.findViewById(R.id.char_detail_fragment_type)!!
        characterGender = view?.findViewById(R.id.char_detail_fragment_gender)!!
        characterOrigin = view?.findViewById(R.id.char_detail_fragment_origin)!!
        characterImage = view?.findViewById(R.id.char_detail_fragment_image)!!
        episodesRecyclerView = view?.findViewById(R.id.char_detail_fragment_episodes_rv)!!
        setupRecyclerView()
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(CHARACTER_ID)) {
            throw RuntimeException("param is absent")
        }
        currentId = args.getInt(CHARACTER_ID)
    }

    private fun setupRecyclerView() {
        episodeAdapter = EpisodeListAdapter()
        episodesRecyclerView.apply {
            adapter = episodeAdapter
            layoutManager = GridLayoutManager(requireActivity(), 2)
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
        private const val CHARACTER_ID = "character_id"

        fun getInstance(id: Int): CharacterDetailFragment {
            return CharacterDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(CHARACTER_ID, id)
                }
            }
        }
    }
}