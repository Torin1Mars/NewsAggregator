package com.example.newsagregatour.MyScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsagregatour.R
import com.example.newsagregatour.ViewModels.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSplashScreen(modifier: Modifier, hideBottomSplashScreen:()-> Unit, myMainViewModel: MainViewModel){

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8F),
        shape = RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp),

        onDismissRequest = {scope.launch { sheetState.hide()}
            .invokeOnCompletion {hideBottomSplashScreen()} },

        dragHandle = null,
        content = {
            Box(modifier = modifier.fillMaxWidth().height(300.dp),
                contentAlignment = Alignment.Center){
                //Background Image
                Image(modifier = modifier.fillMaxSize().size(150.dp),
                    alpha = 0.2F,
                    contentScale = ContentScale.Inside,
                    painter = painterResource(R.drawable.globeicon),
                    contentDescription = stringResource(R.string.SplashScreenBg))

                Text(text =  "This is Example", fontSize = 20.sp, modifier = modifier.clickable {myMainViewModel.addNewCategory("OK")})
            }
        }
    )
}
