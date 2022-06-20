package com.marina.rickandmorty.domain.entity.character

import com.marina.rickandmorty.domain.entity.Info

data class CharactersList(
    val info: Info,
    val results: List<Character>
)