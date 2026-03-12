package com.example.newsagregatour.ViewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsagregatour.Retrofit.RetrofitInstance
import com.example.newsagregatour.data.NewsItem
import com.example.newsagregatour.domain.DbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(@ApplicationContext private val context: Context, private val newsRepository : DbRepository): ViewModel()
{

    init {
        Log.d("MyTag", "Starting")
        loadNewNews()
    }

    val allNewsList = newsRepository.allNews

    fun addNewNews (newNews: NewsItem){
        viewModelScope.launch(Dispatchers.IO) {  // in ViewModel lifecycle
            newsRepository.myNewsTableDao.insertNewNews(newNews) // This call happens on a background thread  Room thread
        }
    }

    fun eraseDbTable(){
        viewModelScope.launch(Dispatchers.IO) { // in ViewModel lifecycle
            newsRepository.myNewsTableDao.delete_all() // This call happens on a background thread  Room thread
        }
    }

    fun loadNewNews(){
        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = RetrofitInstance

            try{
                 val respond = retrofit.api.getLatestNews(apiKey ="pub_6584892198f241e5974c4ce66e37f9aa", query = "pizza", language = "en")

                if(respond.isSuccessful){
                    val myData = respond.body()?.results
                    //TODO
                    print("")

                }else
                    print("")
                    //TODO

            }catch (e: Exception){
                //TODO
            }
        }
    }
}
