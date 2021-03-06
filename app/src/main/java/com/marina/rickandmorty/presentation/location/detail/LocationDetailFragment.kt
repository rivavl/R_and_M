package com.marina.rickandmorty.presentation.location.detail

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marina.rickandmorty.R
import com.marina.rickandmorty.data.character.repository.CharacterRepositoryImpl
import com.marina.rickandmorty.data.location.repository.LocationRepositoryImpl
import com.marina.rickandmorty.domain.character.use_case.GetCharacterUseCase
import com.marina.rickandmorty.domain.character.use_case.GetCharactersUseCase
import com.marina.rickandmorty.domain.location.use_case.GetLocationUseCase
import com.marina.rickandmorty.domain.util.Resource
import com.marina.rickandmorty.presentation.character.detail.CharacterDetailFragment
import com.marina.rickandmorty.presentation.character.list.recycler_view.CharacterListAdapter
import com.marina.rickandmorty.presentation.location.entity.Location


class LocationDetailFragment : Fragment(R.layout.fragment_location_detail) {

    private var currentId: Int = -1

    private lateinit var locationName: TextView
    private lateinit var locationType: TextView
    private lateinit var locationDimension: TextView

    private lateinit var charactersRecyclerView: RecyclerView
    private lateinit var charactersAdapter: CharacterListAdapter

    private lateinit var viewModel: LocationDetailViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setupRecyclerView()
        parseParams()
        val repo = CharacterRepositoryImpl()
        val repoLoc = LocationRepositoryImpl()
        val useCaseChar = GetCharacterUseCase(repo)
        val useCaseChars = GetCharactersUseCase(repo)
        val useCaseLoc = GetLocationUseCase(repoLoc)
        viewModel = ViewModelProvider(
            this,
            LocationDetailViewModelFactory(currentId, useCaseChars, useCaseChar, useCaseLoc)
        )[LocationDetailViewModel::class.java]

        viewModel.location.observe(viewLifecycleOwner) { response ->
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

    private fun setEpisodeInfo(location: Location) {
        locationName.text = location.name
        locationType.text = location.type
        locationDimension.text = location.dimension
    }

    private fun init() {
        locationName = view?.findViewById(R.id.location_detail_fragment_name)!!
        locationType = view?.findViewById(R.id.location_detail_fragment_type)!!
        locationDimension = view?.findViewById(R.id.location_detail_fragment_dimension)!!
        charactersRecyclerView = view?.findViewById(R.id.location_detail_fragment_residents_rv)!!
        setupRecyclerView()
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(LOCATION_ID)) {
            throw RuntimeException("param is absent")
        }
        currentId = args.getInt(LOCATION_ID)
    }

    private fun setupRecyclerView() {
        charactersAdapter = CharacterListAdapter()
        charactersRecyclerView.apply {
            adapter = charactersAdapter
            layoutManager = GridLayoutManager(requireActivity(), 2)
        }
        setupClickListener()
    }

    private fun setupClickListener() {
        charactersAdapter.onCharacterClick = {
            parentFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, CharacterDetailFragment.getInstance(it.id))
                .commit()
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
        private const val LOCATION_ID = "location_id"

        fun getInstance(id: Int): LocationDetailFragment {
            return LocationDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(LOCATION_ID, id)
                }
            }
        }
    }
}