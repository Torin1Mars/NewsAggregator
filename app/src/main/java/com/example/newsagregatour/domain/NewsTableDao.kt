package com.example.newsagregatour.domain

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.newsagregatour.data.NewsItem
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsTableDao {

        //Adding
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertNewNews(newsItem: NewsItem)

        @Insert
        suspend fun insertNewsItem(newsItem: NewsItem)

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertNewsList(newsList: List<NewsItem>)

        //Getting
        @Query("SELECT * FROM NewsTable WHERE newsId = :currentId")
        suspend fun getNewsById(currentId: Int): NewsItem?

        @Query("SELECT * FROM NewsTable")
        fun getAllNews(): Flow<List<NewsItem>>

        @Query("SELECT * FROM NewsTable")
        suspend fun getAllNewsList(): List<NewsItem>

        //Updating
        @Update()
        suspend fun updateNewsItem(newsItem: NewsItem)

        //Deleting
        @Delete
        suspend fun deleteOneNews(myNewsItem: NewsItem)

        @Query("DELETE FROM NewsTable")
        suspend fun clearEntireDB(): Unit

        @Transaction
        suspend fun replaceAllNews(newsItemList: List<NewsItem>){
                clearEntireDB()

                insertNewsList(newsItemList)
        }
}

