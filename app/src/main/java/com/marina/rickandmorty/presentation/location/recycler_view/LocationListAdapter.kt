package com.marina.rickandmorty.presentation.location.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.marina.rickandmorty.R
import com.marina.rickandmorty.presentation.location.entity.Location

class LocationListAdapter :
    ListAdapter<Location, LocationViewHolder>(LocationDiffCallback()) {

    var onEpisodeClick: ((Location) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val layout = R.layout.episode_card_item
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location = getItem(position)

        holder.view.setOnClickListener {
            onEpisodeClick?.invoke(location)
        }

        with(holder) {
            tvName.text = location.name
            tvType.text = location.type
            tvDimension.text = location.dimension
        }
    }
}