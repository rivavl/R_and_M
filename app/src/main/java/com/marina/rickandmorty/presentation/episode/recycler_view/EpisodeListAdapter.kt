package com.marina.rickandmorty.presentation.episode.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.marina.rickandmorty.R
import com.marina.rickandmorty.presentation.episode.entity.Episode

class EpisodeListAdapter :
    ListAdapter<Episode, EpisodeViewHolder>(EpisodeDiffCallback()) {

    var onEpisodeClick: ((Episode) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val layout = R.layout.episode_card_item
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return EpisodeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val episode = getItem(position)

        holder.view.setOnClickListener {
            onEpisodeClick?.invoke(episode)
        }

        with(holder) {
            tvName.text = episode.name
            tvNumber.text = episode.id.toString()
            tvAirDate.text = episode.air_date
        }
    }
}