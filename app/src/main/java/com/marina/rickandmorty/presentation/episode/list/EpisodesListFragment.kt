package com.marina.rickandmorty.presentation.episode.list

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marina.rickandmorty.R
import com.marina.rickandmorty.data.episode.repository.EpisodeRepositoryImpl
import com.marina.rickandmorty.domain.episode.use_case.GetAllEpisodesUseCase
import com.marina.rickandmorty.domain.util.Resource
import com.marina.rickandmorty.presentation.episode.EpisodeViewModelFactory
import com.marina.rickandmorty.presentation.episode.detail.EpisodeDetailFragment
import com.marina.rickandmorty.presentation.episode.list.recycler_view.EpisodeListAdapter
import com.marina.rickandmorty.presentation.util.PaginationScrollListener

class EpisodesListFragment : Fragment(R.layout.fragment_episodes_list) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var errorMessage: TextView
    private lateinit var episodeListAdapter: EpisodeListAdapter

    var isError = false
    var isLoading = false

    lateinit var viewModel: EpisodeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setupRecyclerView()
        val repo = EpisodeRepositoryImpl()
        val useCase = GetAllEpisodesUseCase(repo)
        viewModel = ViewModelProvider(
            this,
            EpisodeViewModelFactory(useCase)
        )[EpisodeViewModel::class.java]

        viewModel.episodesList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    hideErrorMessage()
                    episodeListAdapter.submitList(response.data)
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
        recyclerView = view?.findViewById(R.id.episode_fragment_recycler_view)!!
        progressBar = view?.findViewById(R.id.episode_fragment_progress_bar)!!
        errorMessage = view?.findViewById(R.id.episode_fragment_error_message)!!
    }

    private fun setupRecyclerView() {
        episodeListAdapter = EpisodeListAdapter()
        recyclerView.apply {
            adapter = episodeListAdapter
            layoutManager = GridLayoutManager(activity, 2)
            addOnScrollListener(object :
                PaginationScrollListener(layoutManager as GridLayoutManager) {
                override fun loadMoreItems() {
                    viewModel.getEpisodes()
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
        episodeListAdapter.onEpisodeClick = {
            parentFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, EpisodeDetailFragment.getInstance(it.id))
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