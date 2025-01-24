package com.example.jikan.network

import com.example.jikan.model.AnimeData
import com.example.jikan.model.AnimeListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeApiService {

    @GET("top/anime")
    suspend fun getTopAnime(): Response<AnimeListResponse>

    @GET("anime/{anime_id}")
    suspend fun getAnimeDetails(@Path("anime_id") animeId: Int): AnimeData

}