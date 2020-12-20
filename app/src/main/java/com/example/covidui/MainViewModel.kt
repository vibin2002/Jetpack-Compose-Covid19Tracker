package com.example.covidui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.covidui.repository.Repository
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

    fun getCoronaData() = liveData(Dispatchers.IO) {
        try {
            emit(repository.getCoronaApi())
        }
        catch (e:Exception)
        {
            emit(null)
        }
    }
}