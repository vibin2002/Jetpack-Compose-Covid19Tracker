package com.example.covidui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.covidui.newsapi.Repository
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repository: Repository) : ViewModel(){

    fun getNewsData() = liveData(Dispatchers.IO) {
        try {
            emit(repository.getNewsApi())
        }
        catch (e : Exception)
        {
            emit(null)
        }
    }
}