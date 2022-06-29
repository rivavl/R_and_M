package com.marina.rickandmorty.data.episode.remote

import com.marina.rickandmorty.data.episode.entity.EpisodeDto
import com.marina.rickandmorty.data.episode.entity.EpisodeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodeAPI {

    @GET("episode/")
    suspend fun getEpisodes(
        @Query("page")
        pageNumber: Int = 1
    ): EpisodeResponse

    @GET("episode/{id}")
    suspend fun getEpisodeById(
        @Path("id") id: Int
    ): EpisodeDto
}