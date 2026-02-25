package com.example.newsagregatour.domain

import com.example.newsagregatour.data.NewsItem
import javax.inject.Inject

class DbRepository @Inject constructor(private val newsTableDao: NewsTableDao) {
    val allUsers = newsTableDao.getAllNews()


    suspend fun addNewsItem (newNewsItem: NewsItem){
        newsTableDao.insertNewNews(newNewsItem)
    }

    fun deleteALL(){
        newsTableDao.delete_all()
    }
}
