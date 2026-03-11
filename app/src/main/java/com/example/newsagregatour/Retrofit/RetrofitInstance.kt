package com.example.newsagregatour.Retrofit

import com.example.newsagregatour.ApiData.tempLink
import com.example.newsagregatour.ApiData.urlLink
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = tempLink

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}