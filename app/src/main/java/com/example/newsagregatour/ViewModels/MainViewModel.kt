package com.example.newsagregatour.ViewModels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import retrofit2.Retrofit
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
                val response = retrofit.api.getPosts()
                if(response.isSuccessful){
                    val myData = response.body()

                    Log.d("MyTag", myData.toString())
                }else
                    Log.d("MyTag", "Unsuccessful")


            }catch (e: Exception){
                Log.d("MyTag", "Exception ${e.message}")
            }
        }

    }


}
