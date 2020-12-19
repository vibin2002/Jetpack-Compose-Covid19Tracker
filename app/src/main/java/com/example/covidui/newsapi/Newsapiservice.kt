package com.example.covidui.newsapi

import retrofit2.http.GET

interface Newsapiservice {

    @GET("/v2/everything?q=coronavirus&apiKey=6312f43ad87e4081b662777e4f5b636e")
    suspend fun getNewsData(): Newsfeed

}