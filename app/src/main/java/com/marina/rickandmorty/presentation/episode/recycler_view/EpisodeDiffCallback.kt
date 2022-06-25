package com.marina.rickandmorty.presentation.episode.recycler_view

import androidx.recyclerview.widget.DiffUtil
import com.marina.rickandmorty.presentation.episode.entity.Episode

class EpisodeDiffCallback : DiffUtil.ItemCallback<Episode>() {
    override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem == newItem
    }
}