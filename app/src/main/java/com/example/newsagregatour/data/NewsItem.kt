package com.example.newsagregatour.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NewsTable")
data class NewsItem(
    @PrimaryKey(autoGenerate = true)
    val newsId: Int = 0,

    val newsTitle: String,
    val newsBody : String
)
