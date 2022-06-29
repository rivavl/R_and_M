package com.marina.rickandmorty.data.util

import com.marina.rickandmorty.data.character.remote.CharacterAPI
import com.marina.rickandmorty.data.episode.remote.EpisodeAPI
import com.marina.rickandmorty.data.location.remote.LocationAPI
import com.marina.rickandmorty.data.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val characterApi by lazy {
            retrofit.create(CharacterAPI::class.java)
        }

        val locationApi by lazy {
            retrofit.create(LocationAPI::class.java)
        }

        val episodeApi by lazy {
            retrofit.create(EpisodeAPI::class.java)
        }
    }
}