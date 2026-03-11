package com.example.newsagregatour.Retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    //Basic Get Request
    @GET()
    suspend fun getPosts(): Response<Post>

    // GET request with a dynamic path parameter
    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") postId: Int): Response<Post>

    // GET request with a query parameter
    @GET("posts")
    suspend fun getPostsByUser(@Query("userId") userId: Int): Response<List<Post>>

}