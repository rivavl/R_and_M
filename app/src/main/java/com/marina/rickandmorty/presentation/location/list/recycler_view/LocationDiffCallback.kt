package com.marina.rickandmorty.presentation.location.list.recycler_view

import androidx.recyclerview.widget.DiffUtil
import com.marina.rickandmorty.presentation.location.entity.Location

class LocationDiffCallback : DiffUtil.ItemCallback<Location>() {
    override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
        return oldItem == newItem
    }
}