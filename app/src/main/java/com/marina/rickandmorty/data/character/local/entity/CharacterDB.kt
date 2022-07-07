package com.marina.rickandmorty.data.character.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterDB(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "created")
    val created: String,
    @ColumnInfo(name = "gender")
    val gender: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "location_name")
    val locationName: String,
    @ColumnInfo(name = "location_url")
    val locationUrl: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "origin_name")
    val originName: String,
    @ColumnInfo(name = "origin_url")
    val originUrl: String,
    @ColumnInfo(name = "species")
    val species: String,
    @ColumnInfo(name = "status")
    val status: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "episode")
    val episode: List<String>
)