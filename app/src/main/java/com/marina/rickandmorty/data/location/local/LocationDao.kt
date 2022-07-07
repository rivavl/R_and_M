package com.marina.rickandmorty.data.location.local

import androidx.room.Dao
import androidx.room.Query

@Dao
interface LocationDao {

    @Query("select * from locations where id>=:page and id<(:page+20)")
    suspend fun getLocationsPage(page: Int): List<LocationDB>

    @Query("select * from locations where id=:id")
    suspend fun getLocation(id: Int): LocationDB
}