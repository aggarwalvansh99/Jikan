package com.example.jikan.repo

import com.example.jikan.network.AnimeApiService

class AnimeRepository(private val apiService: AnimeApiService) {

    suspend fun getTopAnime() = apiService.getTopAnime()
    suspend fun getAnimeDetails(animeId: Int) = apiService.getAnimeDetails(animeId)
}