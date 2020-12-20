package com.example.covidui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.covidui.newsapi.ApiHelper

class MainViewModelFactory(val repository: Repository)  : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository = repository) as T

    }
}