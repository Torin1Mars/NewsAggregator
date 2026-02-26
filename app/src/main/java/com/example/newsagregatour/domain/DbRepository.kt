package com.example.newsagregatour.domain

import com.example.newsagregatour.data.NewsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DbRepository @Inject constructor(private val newsTableDao: NewsTableDao) {
    val allNews = newsTableDao.getAllNews()
    val myNewsTableDao = newsTableDao
}
