package com.marina.rickandmorty.presentation.character.detail.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marina.rickandmorty.R
import com.marina.rickandmorty.presentation.episode.entity.Episode
import com.marina.rickandmorty.presentation.episode.list.recycler_view.EpisodeDiffCallback

class EpisodeAdapter :
    ListAdapter<Episode, EpisodeAdapter.eViewHolder>(EpisodeDiffCallback()) {

    var onCharacterClick: ((Episode) -> Unit)? = null

    class eViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.episode_card_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): eViewHolder {
        val layout = R.layout.char_episode_card_item
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return eViewHolder(view)
    }

    override fun onBindViewHolder(holder: eViewHolder, position: Int) {
        val episode = getItem(position)

        holder.view.setOnClickListener {
            onCharacterClick?.invoke(episode)
        }

        with(holder) {
            name.text = episode.name
        }
    }
}