package com.example.newsagregatour.Retrofit

data class NewsResponse (
    val status : String,
    val totalResults: Int,
    val results: List<Article>
)

data class Article(
    val title: String,
    val link: String,
    val description: String?,
    val image_url: String?,
    val video_url : String?
)
