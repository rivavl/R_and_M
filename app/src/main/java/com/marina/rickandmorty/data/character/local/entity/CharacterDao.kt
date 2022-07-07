package com.marina.rickandmorty.data.character.local.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(characterDB: CharacterDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(charactersDB: List<CharacterDB>)

    @Query("select * from characters where id>=:page and id<(:page+20)")
    suspend fun getCharactersPage(page: Int): List<CharacterDB>

    @Query("select * from characters where id=:id")
    suspend fun getCharacter(id: Int): CharacterDB

    @Query(
        "select * from characters " +
                "where name like '%' || :name || '%' " +
                "and status like '%' || :status || '%' " +
                "and gender like '%' || :gender || '%'" +
                "and species like '%' || :species || '%'" +
                "and type like '%' || :type || '%'"
    )
    fun findCharacters(
        name: String,
        status: String,
        gender: String,
        species: String,
        type: String
    ): List<CharacterDB>
}









