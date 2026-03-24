package com.example.newsagregatour.domain

import com.example.newsagregatour.data.NewsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.future.asCompletableFuture
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject
import kotlin.math.sin

class DbRepository @Inject constructor(private val newsTableDao: NewsTableDao) {

    @ExperimentalSerializationApi
    val allNews = newsTableDao.getAllNews()

    val myNewsTableDao = newsTableDao

    suspend fun getNewsCount(): Int{
        var count : Int = 0

        count = myNewsTableDao.getAllNewsList().size
        return count
    }

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
