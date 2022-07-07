package com.marina.rickandmorty.data.character.repository

import android.app.Application
import android.content.Context
import com.marina.rickandmorty.data.character.local.entity.CharacterDB
import com.marina.rickandmorty.data.character.local.entity.CharacterDao
import com.marina.rickandmorty.data.character.local.entity.toCharacterDB
import com.marina.rickandmorty.data.character.remote.CharacterAPI
import com.marina.rickandmorty.data.character.remote.entity.CharacterDto
import com.marina.rickandmorty.data.episode.local.EpisodeDB
import com.marina.rickandmorty.data.util.database.DatabaseRM
import com.marina.rickandmorty.data.util.network.RetrofitInstance
import com.marina.rickandmorty.domain.character.repository.CharacterRepository

class CharacterRepositoryImpl(
//    private val api: CharacterAPI,
//    private val dao: CharacterDao
    val context: Context
) : CharacterRepository {

    private val api: CharacterAPI = RetrofitInstance.characterApi
    private val dao: CharacterDao = DatabaseRM.getInstance(context as Application).characterDao

    override suspend fun getAllCharacters(pageNumber: Int): List<CharacterDto> {
        return api.getCharacters(pageNumber).characterDtos
    }

    override suspend fun getCharacter(id: Int): List<CharacterDto> {
        return listOf(api.getCharacterById(id))
    }

    override suspend fun getCharacters(ids: String): List<CharacterDto> {
        val data = api.getCharactersByIds(ids)

        return data
    }

    override suspend fun getCharactersDB(pageNumber: Int): List<CharacterDB> {
        return dao.getCharactersPage(pageNumber)
    }

    override suspend fun getCharacterDB(id: Int): CharacterDB {
        return dao.getCharacter(id)
    }

    override suspend fun insertCharacter(characterDB: CharacterDB) {
        dao.insertCharacter(characterDB)
    }

    override suspend fun getEpisodesOfCharacterDB(characterId: Int): List<EpisodeDB> {
        return dao.getEpisodesOfCharacter(characterId)
    }

    suspend fun insertCharacterEpisodeRelation(chId: Int, epId: Int?) {
        dao.insertCharacterEpisode(chId, epId)
    }
}