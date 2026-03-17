package com.example.newsagregatour.domain

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.newsagregatour.Retrofit.Article
import com.example.newsagregatour.data.NewsItem
import com.google.gson.Gson
import kotlinx.serialization.json.Json

@Database(entities = [NewsItem::class], version = 1, exportSchema = false)
@TypeConverters(ArticleConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun newsTableDao(): NewsTableDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "NewsDatabase"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

class ArticleConverter(){
    @TypeConverter
    fun convertToInsert(article: Article): String{
        return Gson().toJson(article)
    }

    @TypeConverter
    fun convertToGet(convertedArticle: String): Article {
        return Gson().fromJson(convertedArticle, Article::class.java)
    }
}
