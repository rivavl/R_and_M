package com.marina.rickandmorty.data.util.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marina.rickandmorty.data.character.local.entity.CharacterDB
import com.marina.rickandmorty.data.character.local.entity.CharacterDao
import com.marina.rickandmorty.data.character.local.entity.CharacterWithEpisodesDB
import com.marina.rickandmorty.data.episode.local.EpisodeDB
import com.marina.rickandmorty.data.episode.local.EpisodeDao
import com.marina.rickandmorty.data.location.local.LocationDB
import com.marina.rickandmorty.data.location.local.LocationDao

@Database(
    entities = [
        CharacterDB::class,
        EpisodeDB::class,
        LocationDB::class,
        CharacterWithEpisodesDB::class
    ], version = 1, exportSchema = false
)
abstract class DatabaseRM : RoomDatabase() {

    abstract val characterDao: CharacterDao
    abstract val episodeDao: EpisodeDao
    abstract val locationDao: LocationDao


    companion object {
        private var INSTANCE: DatabaseRM? = null
        private val LOCK = Any()
        private const val DB_NAME = "rick_and_morty.db"

        fun getInstance(application: Application): DatabaseRM {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    DatabaseRM::class.java,
                    DB_NAME
                )
                    .build()
                INSTANCE = db
                return db
            }
        }
    }
}