package com.example.covidui

import com.example.covidui.newsapi.ApiHelper
import com.example.covidui.newsapi.RetrofitBuilder

class Repository()
{
    suspend fun getNewsApi() = RetrofitBuilder.newsapiservice.getNewsData()

    suspend fun getCoronaApi() = RetrofitBuilder.coronaApiSevice.getCoronaData()
}