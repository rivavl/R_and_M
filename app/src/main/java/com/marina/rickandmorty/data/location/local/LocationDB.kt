package com.marina.rickandmorty.data.location.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations")
data class LocationDB(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val created: String?,
    val dimension: String?,
    val name: String?,
    val type: String?,
)