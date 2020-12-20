package com.example.covidui.newsapi.newsmodel

import com.example.covidui.newsapi.newsmodel.Article

data class Newsfeed(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)