package com.example.covidui.statsapi

import retrofit2.http.GET

interface CoronaApiSevice {
    @GET("/v3/covid-19/all")
    suspend fun getCoronaData(): Corona
}