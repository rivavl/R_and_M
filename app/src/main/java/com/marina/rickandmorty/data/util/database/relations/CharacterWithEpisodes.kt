package com.marina.rickandmorty.data.util.database.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.marina.rickandmorty.data.character.local.entity.CharacterDB
import com.marina.rickandmorty.data.episode.local.EpisodeDB

data class CharacterWithEpisodes(

    @Embedded
    val characterDB: CharacterDB,

    @Relation(
        parentColumn = "characterId",
        entityColumn = "episodeId",
        associateBy = Junction((CharacterEpisodeCrossRef::class))
    )
    val episodes: List<EpisodeDB>
)
