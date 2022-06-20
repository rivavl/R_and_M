package com.marina.rickandmorty.domain.entity.episode

import com.marina.rickandmorty.domain.entity.Info

data class EpisodesList(
    val info: Info,
    val results: List<Episode>
)