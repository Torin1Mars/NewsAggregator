package com.example.newsagregatour.ViewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsagregatour.data.NewsItem
import com.example.newsagregatour.domain.AppDatabase
import com.example.newsagregatour.domain.DbRepository
import com.example.newsagregatour.domain.NewsTableDao
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(@ApplicationContext private val context: Context, private val newsRepository : DbRepository): ViewModel()
{

    init {
        Log.d("MyLog", "View model created")
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
}
