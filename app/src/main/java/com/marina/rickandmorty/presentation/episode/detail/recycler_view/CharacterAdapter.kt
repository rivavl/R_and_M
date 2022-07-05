package com.marina.rickandmorty.presentation.episode.list.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marina.rickandmorty.R
import com.marina.rickandmorty.presentation.character.entity.Character
import com.marina.rickandmorty.presentation.character.list.recycler_view.CharacterDiffCallback
import com.marina.rickandmorty.presentation.character.list.recycler_view.CharacterViewHolder

class CharacterAdapter :
    ListAdapter<Character, CharacterAdapter.chViewHolder>(CharacterDiffCallback()) {

    var onCharacterClick: ((Character) -> Unit)? = null

    class chViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.character_card_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): chViewHolder {
        val layout = R.layout.episode_char_card_item
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return chViewHolder(view)
    }

    override fun onBindViewHolder(holder: chViewHolder, position: Int) {
        val character = getItem(position)

        holder.view.setOnClickListener {
            onCharacterClick?.invoke(character)
        }

        with(holder) {
            tvName.text = character.name
        }
    }
}