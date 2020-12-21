package com.example.covidui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.covidui.repository.Repository
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repository: Repository) : ViewModel(){

    var newsvm = liveData(Dispatchers.IO) {
        try {
            emit(repository.getNewsApi())
        }
        catch (e : Exception)
        {
            emit(null)
        }
    }

    var coronavm = liveData(Dispatchers.IO) {
        try {
            emit(repository.getCoronaApi())
        }
        catch (e:Exception)
        {
            emit(null)
        }
    }
}