package com.example.jikan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jikan.repo.AnimeRepository
import com.example.jikan.viewmodels.AnimeDetailsViewModel
import com.example.jikan.viewmodels.AnimeListViewModel

class ViewModelFactory(private val repository: AnimeRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AnimeListViewModel::class.java) -> AnimeListViewModel(repository) as T
            modelClass.isAssignableFrom(AnimeDetailsViewModel::class.java) -> AnimeDetailsViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}