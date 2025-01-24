package com.example.jikan.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.jikan.model.TopAnimeResponse
import com.example.jikan.repo.AnimeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AnimeListViewModel(private val repository: AnimeRepository) : ViewModel() {
    private val _topAnime: MutableLiveData<TopAnimeResponse> = MutableLiveData()
    val topAnimeResponse: MutableLiveData<TopAnimeResponse> =_topAnime

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

    fun fetchTopAnimee(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getTopAnime()
            if(response.isSuccessful&& response.body()!=null){
                _topAnime.postValue(response.body())
            }
            else{
                println("Error: ${response.errorBody()?.string()}")
            }

        }
    }
}