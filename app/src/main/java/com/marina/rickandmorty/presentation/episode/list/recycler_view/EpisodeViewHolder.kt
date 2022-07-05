package com.marina.rickandmorty.presentation.episode.list.recycler_view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marina.rickandmorty.R

class EpisodeViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val tvNumber = view.findViewById<TextView>(R.id.episode_card_count)
    val tvName = view.findViewById<TextView>(R.id.episode_card_name)
    val tvAirDate = view.findViewById<TextView>(R.id.episode_card_air_date)
}