package com.marina.rickandmorty.presentation.character.list.recycler_view

import androidx.recyclerview.widget.DiffUtil
import com.marina.rickandmorty.presentation.character.entity.Character

class CharacterDiffCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}