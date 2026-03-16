package com.example.newsagregatour.Retrofit

import kotlinx.serialization.Serializable

data class NewsResponse (
    val status : String,
    val totalResults: Int,
    val results: List<Article>
)

@Serializable
data class Article(
    val title: String,
    val link: String,
    val description: String?,
    val image_url: String?,
    val video_url : String?
)
