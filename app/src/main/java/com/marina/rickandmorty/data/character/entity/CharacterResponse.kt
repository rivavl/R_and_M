package com.marina.rickandmorty.data.character.entity

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    val info: Info,
    @SerializedName("results")
    val characterDtos: List<CharacterDto>
)