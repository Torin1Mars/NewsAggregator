package com.example.newsagregatour.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsagregatour.data.NewsItem
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsTableDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertNewNews(newsItem: NewsItem)

        @Query("SELECT * FROM NewsTable")
        fun getAllNews(): Flow<List<NewsItem>>

        @Query("DELETE FROM NewsTable")
        fun delete_all(): Unit
}
