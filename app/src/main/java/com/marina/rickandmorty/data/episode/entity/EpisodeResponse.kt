package com.marina.rickandmorty.data.episode.entity

import com.google.gson.annotations.SerializedName

data class EpisodeResponse(
    val info: Info,
    @SerializedName("results")
    val episodeDtos: List<EpisodeDto>
)