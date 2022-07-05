package com.marina.rickandmorty.presentation.character.list.recycler_view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marina.rickandmorty.R

class CharacterViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val ivPhoto = view.findViewById<ImageView>(R.id.character_card_photo)
    val tvName = view.findViewById<TextView>(R.id.character_card_name)
    val tvStatus = view.findViewById<TextView>(R.id.character_card_status)
    val tvSpecies = view.findViewById<TextView>(R.id.character_card_species)
    val tvGender = view.findViewById<TextView>(R.id.character_card_gender)
}