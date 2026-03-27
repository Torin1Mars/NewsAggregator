package com.example.newsagregatour.MyScreens

import androidx.compose.foundation.background
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.newsagregatour.ViewModels.MainViewModel
import com.example.newsagregatour.data.NewsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.newsagregatour.R
import com.example.newsagregatour.ui.theme.singleScreenBrush
import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(ExperimentalSerializationApi::class)
@Composable
fun SingleNewsScreen(newsId: String){

    val modifier = Modifier

    val viewModel  = hiltViewModel<MainViewModel>()

    var currentNewsItem by remember {mutableStateOf<NewsItem?>(null)}
    CoroutineScope(Dispatchers.IO).launch{
        currentNewsItem = viewModel.getNewsById(newsId.toInt())
    }

    Box(modifier = modifier.fillMaxSize()
        .background(brush = singleScreenBrush)) {
        Column(modifier = modifier.fillMaxSize()
            .padding(top = 20.dp)
            .padding(horizontal = 10.dp)) {

            val article = currentNewsItem?.newsBody

            currentNewsItem?.let {it ->
                AsyncImage(model = it.newsBody.image_url,
                    modifier = modifier.clip(RoundedCornerShape(5.dp)),
                    contentDescription = stringResource(R.string.PreviewImg),
                    alignment = Alignment.Center
                )

                HorizontalDivider(modifier = modifier.fillMaxWidth()
                    .padding(top = 5.dp)
                    .padding(horizontal = 5.dp),
                    thickness = 3.dp,
                    color = Color.Gray)
            }

            Box(modifier = modifier.fillMaxSize().padding(top = 10.dp))
            {
                Surface(modifier = modifier.fillMaxSize(),
                    shape = RoundedCornerShape(5.dp),
                    color = Color.White.copy(alpha = 0.6f)){}

                Column (modifier = modifier.fillMaxSize()){
                    Spacer(modifier = modifier.fillMaxWidth().height(15.dp))

                    currentNewsItem?.let {
                        Text(text = it.newsTitle,
                            modifier = modifier.align (Alignment.CenterHorizontally),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = modifier.fillMaxWidth()
                        .height(15.dp))

                    article?.description?.let{
                        Text(text = article.description,
                            fontSize = 16.sp,
                            fontStyle = FontStyle.Normal,
                            style = TextStyle(lineHeight = 1.2.em))
                    }
                }
            }
        }
    }
}
