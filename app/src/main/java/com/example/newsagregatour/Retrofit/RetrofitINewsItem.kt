package com.example.newsagregatour.Retrofit

import com.example.newsagregatour.SupportingData.URL_Link

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitINewsItem {
    private const val BASE_URL = URL_Link

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api : GeneralNewsApiService by lazy {
        retrofit.create(GeneralNewsApiService::class.java)
    }
}


