package com.example.covidui.apis.newsmodel

data class Newsfeed(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)