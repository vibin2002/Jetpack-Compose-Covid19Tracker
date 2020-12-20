package com.example.covidui.apis

class ApiHelper(private val apiservice1: Apiservice,private val apiservice2: Apiservice) {

    suspend fun getNewsdata() = apiservice1.getNewsData()

    suspend fun getCoronaData() = apiservice2.getCoronaData()

}