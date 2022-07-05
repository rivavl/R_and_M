package com.marina.rickandmorty.presentation.location.list.recycler_view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marina.rickandmorty.R

class LocationViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val tvName = view.findViewById<TextView>(R.id.location_card_name)
    val tvType = view.findViewById<TextView>(R.id.location_card_type)
    val tvDimension = view.findViewById<TextView>(R.id.location_card_dimension)
}