package com.example.newsagregatour.domain

import com.example.newsagregatour.data.NewsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

class DbRepository @Inject constructor(private val newsTableDao: NewsTableDao) {

    @ExperimentalSerializationApi
    val allNews = newsTableDao.getAllNews()

    val myNewsTableDao = newsTableDao

    @ExperimentalSerializationApi
    suspend fun insertNewNews (newNews : NewsItem){
            newsTableDao.insertNewNews(newNews)
        }

    suspend fun clearDB(){
        newsTableDao.clearEntireDB()
    }
    @ExperimentalSerializationApi
    suspend fun refreshNewsList(myNewNewsList: List<NewsItem>){
        newsTableDao.replaceAllNews(myNewNewsList)
    }
}
