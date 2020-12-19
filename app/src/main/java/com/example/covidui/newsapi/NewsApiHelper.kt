package com.example.covidui.newsapi

class NewsApiHelper(private val newsapiservice: Newsapiservice) {
    suspend fun getNewsdata() = newsapiservice.getNewsData()
}