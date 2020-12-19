package com.example.covidui.newsapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsRetrofitBuilder {
    private const val BASE_URL = "https://newsapi.org"

    private fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService : Newsapiservice = getRetrofit().create(Newsapiservice::class.java)
}