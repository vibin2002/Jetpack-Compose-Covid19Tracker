package com.example.covidui

import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.res.imageResource

data class NewsFeed(
        val title: String,
        val imageAsset: Int
)

fun getNewsFeed() = listOf<NewsFeed>(
        NewsFeed("America’s First Coronavirus Vaccinations America’s First Coronavirus Vaccinations", imageAsset =  R.drawable.sample_image),
        NewsFeed("America’s First Coronavirus Vaccinations America’s First Coronavirus Vaccinations", imageAsset =  R.drawable.sample_image),
        NewsFeed("America’s First Coronavirus Vaccinations", imageAsset =  R.drawable.sample_image),
        NewsFeed("America’s First Coronavirus Vaccinations", imageAsset =  R.drawable.sample_image),
        NewsFeed("America’s First Coronavirus Vaccinations", imageAsset =  R.drawable.sample_image),
        NewsFeed("America’s First Coronavirus Vaccinations", imageAsset =  R.drawable.sample_image),
        NewsFeed("America’s First Coronavirus Vaccinations", imageAsset =  R.drawable.sample_image),
        NewsFeed("America’s First Coronavirus Vaccinations", imageAsset =  R.drawable.sample_image),
        NewsFeed("America’s First Coronavirus Vaccinations", imageAsset =  R.drawable.sample_image)
)