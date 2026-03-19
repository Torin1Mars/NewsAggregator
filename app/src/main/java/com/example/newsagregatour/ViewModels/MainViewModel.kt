package com.example.newsagregatour.ViewModels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsagregatour.Retrofit.Article
import com.example.newsagregatour.Retrofit.RetrofitINewsItem
import com.example.newsagregatour.SupportingData.standardNewsCategories
import com.example.newsagregatour.data.NewsItem
import com.example.newsagregatour.domain.DbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import java.util.concurrent.Flow
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(@ApplicationContext private val context: Context, private val newsRepository : DbRepository): ViewModel()
{
    init {
        Log.d("MyTag", "View model was created")
        //loadNewNews()
        //clearDB()
    }

    @ExperimentalSerializationApi
    val allNewsList = newsRepository.allNews

    @ExperimentalSerializationApi
    fun addNewNews (newNews: NewsItem){
        viewModelScope.launch(Dispatchers.IO) {  // in ViewModel lifecycle
            newsRepository.insertNewNews(newNews) // This call happens on a background thread  Room thread
        }
    }

    @ExperimentalSerializationApi
    fun updateNewsList(newsList: List<NewsItem>){
        viewModelScope.launch(Dispatchers.IO) {  // in ViewModel lifecycle
            newsRepository.refreshNewsList(newsList)

            withContext (Dispatchers.Main) {
                makeToast("Updated")
            }
        }
    }

    fun clearDB(){
        viewModelScope.launch(Dispatchers.IO) { // in ViewModel lifecycle
            newsRepository.clearDB() // This call happens on a background thread  Room thread
        }
    }

    fun makeToast(message: String, duration: Int  = Toast.LENGTH_SHORT){
        Toast.makeText(context, message, duration).show()
    }

    fun loadNewNews(){
        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = RetrofitINewsItem

            try{
                val respond = retrofit.api.getLatestNews(apiKey ="pub_6584892198f241e5974c4ce66e37f9aa", query = "pizza", language = "en")

                if(respond.isSuccessful){
                    val myData = respond.body()?.results
                    if (!myData.isNullOrEmpty())
                        addNewsToDb(myData)
                    else{
                        //Do nothing
                    }

                }else
                    makeToast("Couldn't refresh news, try again later")

            }catch (e: Exception){
                makeToast("Couldn't refresh news, check your connection")
            }
        }
    }

    @ExperimentalSerializationApi
    fun addNewsToDb(newsList: List<Article>){
        val myNewsNewsList = mutableStateListOf<NewsItem>()

        newsList.forEach {
            it -> myNewsNewsList.add(NewsItem(newsTitle = it.title, newsBody = it)
            )
        }
        updateNewsList(myNewsNewsList)
    }

    // For Categories
    val myCategories = standardNewsCategories.toMutableStateList()

    fun addNewCategory(newCategory: String){
        myCategories.add(newCategory)
        Log.d("MyTag", myCategories.toString())
    }

}
