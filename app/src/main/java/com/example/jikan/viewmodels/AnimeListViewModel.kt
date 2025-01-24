package com.example.jikan.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.jikan.repo.AnimeRepository
import kotlinx.coroutines.Dispatchers



class AnimeListViewModel(private val repository: AnimeRepository) : ViewModel() {


    fun fetchTopAnime() = liveData(Dispatchers.IO) {

        try {
            val response = repository.getTopAnime()
            if(!response.isSuccessful){
                println("Error: ${response.errorBody()?.string()}")
            }
            emit(response)
        } catch (e: Exception) {
            emit(null)
        }
    }
}