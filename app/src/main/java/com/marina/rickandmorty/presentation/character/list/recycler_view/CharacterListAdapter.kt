package com.marina.rickandmorty.presentation.character.list.recycler_view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.marina.rickandmorty.R
import com.marina.rickandmorty.presentation.character.entity.Character

class CharacterListAdapter :
    ListAdapter<Character, CharacterViewHolder>(CharacterDiffCallback()) {

    var onCharacterClick: ((Character) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layout = R.layout.character_card_item
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)

        holder.view.setOnClickListener {
            onCharacterClick?.invoke(character)
        }

        with(holder) {
            tvName.text = character.name
            tvStatus.text = character.status
            tvSpecies.text = character.species
            tvGender.text = character.gender
            loadImage(character.image, ivPhoto, holder.view.context)
        }
    }

    private fun loadImage(url: String, imageView: ImageView, context: Context) {
        Glide.with(context)
            .load(url)
            .into(imageView)
    }
}