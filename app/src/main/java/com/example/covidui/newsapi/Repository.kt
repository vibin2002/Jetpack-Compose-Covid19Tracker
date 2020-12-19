package com.example.covidui.newsapi

class Repository(private val newsApiHelper: NewsApiHelper)
{
    suspend fun getNewsApi() = newsApiHelper.getNewsdata()
}