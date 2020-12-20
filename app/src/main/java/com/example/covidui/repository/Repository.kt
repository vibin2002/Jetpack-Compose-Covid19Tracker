package com.example.covidui.repository

import com.example.covidui.apis.RetrofitBuilder

class Repository()
{
    suspend fun getNewsApi() = RetrofitBuilder.newsapiservice.getNewsData()

    suspend fun getCoronaApi() = RetrofitBuilder.coronaApiSevice.getCoronaData()
}