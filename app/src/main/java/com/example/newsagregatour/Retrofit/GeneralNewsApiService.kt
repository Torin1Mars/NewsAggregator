package com.example.newsagregatour.Retrofit


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GeneralNewsApiService {
    @GET("latest")
    suspend fun getLatestNews(
        @Query("apikey") apiKey: String,
        @Query("q") query: String? = null,
        @Query("language") language: String = "en"
    ): Response<NewsResponse>
}

