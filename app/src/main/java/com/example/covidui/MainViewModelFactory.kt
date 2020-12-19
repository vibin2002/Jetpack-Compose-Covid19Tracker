package com.example.covidui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.covidui.newsapi.NewsApiHelper
import com.example.covidui.newsapi.Repository

class MainViewModelFactory(private val newsApiHelper: NewsApiHelper)  : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(Repository(newsApiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}