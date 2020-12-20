package com.example.covidui.newsapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    companion object {

        private  val BASE_URL1 = "https://newsapi.org"
        private  val BASE_URL2 = "https://disease.sh"

        private fun getNewsRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL1)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        private fun getCoronaRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL2)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val newsapiservice: Apiservice = getNewsRetrofit().create(Apiservice::class.java)
        val coronaApiSevice: Apiservice = getCoronaRetrofit().create(Apiservice::class.java)
    }
}