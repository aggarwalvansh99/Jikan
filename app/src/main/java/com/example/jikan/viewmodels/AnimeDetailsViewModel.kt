package com.example.jikan.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jikan.repo.AnimeRepository
import com.example.jikan.model.AnimeData
import kotlinx.coroutines.launch

class AnimeDetailsViewModel(private val repository: AnimeRepository) : ViewModel() {

    private val _animeDetails = MutableLiveData<AnimeData?>()
    val animeDetails: LiveData<AnimeData?> = _animeDetails

    fun fetchAnimeDetails(animeId: Int) {
        viewModelScope.launch {
            try {
                _animeDetails.value = repository.getAnimeDetails(animeId)
            } catch (e: Exception) {
                _animeDetails.value = null
            }
        }
    }
}