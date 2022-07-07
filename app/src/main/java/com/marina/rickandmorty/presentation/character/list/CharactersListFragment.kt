package com.marina.rickandmorty.presentation.character.list

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marina.rickandmorty.R
import com.marina.rickandmorty.data.character.remote.CharacterAPI
import com.marina.rickandmorty.domain.util.Resource
import com.marina.rickandmorty.data.character.repository.CharacterRepositoryImpl
import com.marina.rickandmorty.domain.character.use_case.GetAllCharactersUseCase
import com.marina.rickandmorty.presentation.character.CharacterViewModelFactory
import com.marina.rickandmorty.presentation.character.detail.CharacterDetailFragment
import com.marina.rickandmorty.presentation.character.list.recycler_view.CharacterListAdapter
import com.marina.rickandmorty.presentation.util.PaginationScrollListener


class CharactersListFragment : Fragment(R.layout.fragment_characters_list) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var errorMessage: TextView
    private lateinit var characterListAdapter: CharacterListAdapter

    var isError = false
    var isLoading = false

    lateinit var viewModel: CharacterViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setupRecyclerView()
        val repo = CharacterRepositoryImpl(requireActivity().applicationContext)
        val useCase = GetAllCharactersUseCase(repo)
        viewModel = ViewModelProvider(
            this,
            CharacterViewModelFactory(useCase, requireActivity().applicationContext)
        )[CharacterViewModel::class.java]

        viewModel.charactersList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    hideErrorMessage()
                    characterListAdapter.submitList(response.data)
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Error -> {
                    response.message?.let { msg -> showErrorMessage(msg) }
                }
            }
        }
    }

    private fun init() {
        recyclerView = view?.findViewById(R.id.char_fragment_recycler_view)!!
        progressBar = view?.findViewById(R.id.char_list_progress_bar)!!
        errorMessage = view?.findViewById(R.id.char_fragment_error_message)!!
    }

    private fun setupRecyclerView() {
        characterListAdapter = CharacterListAdapter()
        recyclerView.apply {
            adapter = characterListAdapter
            layoutManager = GridLayoutManager(activity, 2)
            addOnScrollListener(object :
                PaginationScrollListener(layoutManager as GridLayoutManager) {
                override fun loadMoreItems() {
                    viewModel.getCharacters()
                }

                override fun isLastPage(): Boolean {
                    return false
                }

                override fun isLoading(): Boolean {
                    return isLoading
                }
            })
        }
        setupClickListener()
    }

    private fun setupClickListener() {
        characterListAdapter.onCharacterClick = {
            parentFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, CharacterDetailFragment.getInstance(it.id))
                .commit()
        }
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
        isLoading = true
    }

    private fun hideErrorMessage() {
        errorMessage.visibility = View.INVISIBLE
        isError = false
    }

    private fun showErrorMessage(message: String) {
        errorMessage.visibility = View.VISIBLE
        errorMessage.text = message
        isError = true
    }
}