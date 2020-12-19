package com.example.covidui.newsapi

data class Newsfeed(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)