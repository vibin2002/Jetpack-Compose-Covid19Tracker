package com.example.covidui.newsapi

import com.example.covidui.newsapi.newsmodel.Newsfeed
import com.example.covidui.statsapi.Corona
import retrofit2.http.GET

interface Apiservice {

    @GET("/v2/everything?q=coronavirus&apiKey=6312f43ad87e4081b662777e4f5b636e")
    suspend fun getNewsData(): Newsfeed

    @GET("/v3/covid-19/all")
    suspend fun getCoronaData(): Corona

}