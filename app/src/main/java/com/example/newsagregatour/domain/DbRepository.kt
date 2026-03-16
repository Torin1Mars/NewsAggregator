package com.example.newsagregatour.domain

import com.example.newsagregatour.data.NewsItem
import javax.inject.Inject

class DbRepository @Inject constructor(private val newsTableDao: NewsTableDao) {

    val allNews = newsTableDao.getAllNews()

    val myNewsTableDao = newsTableDao

    suspend fun insertNewNews (newNews : NewsItem){
            newsTableDao.insertNewNews(newNews)
        }

    suspend fun clearDB(){
        newsTableDao.clearEntireDB()
    }

    suspend fun refreshNewsList(myNewNewsList: List<NewsItem>){
        newsTableDao.replaceAllNews(myNewNewsList)
    }
}
