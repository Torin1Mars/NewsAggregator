package com.example.newsagregatour.Retrofit

import java.util.Objects

/*
class Post (
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)*/

class Post (
    val status : String,
    val totalResults: Int,
    val results: List<Objects>
)