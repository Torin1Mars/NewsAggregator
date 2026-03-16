package com.example.newsagregatour.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsagregatour.Retrofit.Article
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.UseSerializers

@ExperimentalSerializationApi
@Entity(tableName = "NewsTable")
data class NewsItem(
    @PrimaryKey(autoGenerate = true)
    val newsId: Int = 0,

    val newsTitle: String,
    val newsBody : Article
)
