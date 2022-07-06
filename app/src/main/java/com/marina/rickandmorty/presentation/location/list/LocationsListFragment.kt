package com.marina.rickandmorty.presentation.location.list

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marina.rickandmorty.R
import com.marina.rickandmorty.data.location.repository.LocationRepositoryImpl
import com.marina.rickandmorty.domain.location.use_case.GetAllLocationsUseCase
import com.marina.rickandmorty.domain.util.Resource
import com.marina.rickandmorty.presentation.episode.detail.EpisodeDetailFragment
import com.marina.rickandmorty.presentation.location.LocationViewModelFactory
import com.marina.rickandmorty.presentation.location.detail.LocationDetailFragment
import com.marina.rickandmorty.presentation.location.list.recycler_view.LocationListAdapter
import com.marina.rickandmorty.presentation.util.PaginationScrollListener

class LocationsListFragment : Fragment(R.layout.fragment_locations_list) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var errorMessage: TextView
    private lateinit var locationListAdapter: LocationListAdapter

    var isError = false
    var isLoading = false

    lateinit var viewModel: LocationViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setupRecyclerView()
        val repo = LocationRepositoryImpl()
        val useCase = GetAllLocationsUseCase(repo)
        viewModel = ViewModelProvider(
            this,
            LocationViewModelFactory(useCase)
        )[LocationViewModel::class.java]

        viewModel.locationsList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    hideErrorMessage()
                    locationListAdapter.submitList(response.data)
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
        recyclerView = view?.findViewById(R.id.location_fragment_recycler_view)!!
        progressBar = view?.findViewById(R.id.location_fragment_progress_bar)!!
        errorMessage = view?.findViewById(R.id.location_fragment_error_message)!!
    }

    private fun setupRecyclerView() {
        locationListAdapter = LocationListAdapter()
        recyclerView.apply {
            adapter = locationListAdapter
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
        locationListAdapter.onLocationClick = {
            parentFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, LocationDetailFragment.getInstance(it.id))
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