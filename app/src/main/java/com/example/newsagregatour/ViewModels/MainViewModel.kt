package com.example.newsagregatour.ViewModels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsagregatour.Retrofit.Article
import com.example.newsagregatour.Retrofit.RetrofitInstance
import com.example.newsagregatour.data.NewsItem
import com.example.newsagregatour.domain.DbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.time.Duration

@HiltViewModel
class MainViewModel @Inject constructor(@ApplicationContext private val context: Context, private val newsRepository : DbRepository): ViewModel()
{
    init {
        loadNewNews()
    }

    val allNewsList = newsRepository.allNews

    fun addNewNews (newNews: NewsItem){
        viewModelScope.launch(Dispatchers.IO) {  // in ViewModel lifecycle
            newsRepository.insertNewNews(newNews) // This call happens on a background thread  Room thread
        }
    }

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
            val retrofit = RetrofitInstance

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

    fun addNewsToDb(newsList: List<Article>){
        val myNewsNewsList = mutableStateListOf<NewsItem>()

        newsList.forEach {
            it -> myNewsNewsList.add(NewsItem(newsTitle = it.title, newsBody = it.description!!))
        }

        updateNewsList(myNewsNewsList)
    }
}
