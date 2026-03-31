package com.example.newsagregatour.MyScreens

import android.R.attr.textStyle
import android.content.Context
import android.graphics.drawable.Icon
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.RemoteInput
import androidx.room.util.TableInfo
import com.example.newsagregatour.R
import com.example.newsagregatour.ViewModels.MainViewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSplashScreen(context: Context, modifier: Modifier, hideBottomSplashScreen:()-> Unit, myMainViewModel: MainViewModel){

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    val modalScreenHeight = 300.dp

    var userInput  by rememberSaveable { mutableStateOf("")}
    var btnState  by rememberSaveable { mutableStateOf(false)}

    if (userInput.isNotEmpty()){
        btnState = true
    }

    ModalBottomSheet(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8F),
        shape = RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp),

        onDismissRequest = {scope.launch {sheetState.hide()}
            .invokeOnCompletion {hideBottomSplashScreen()} },

        dragHandle = null,
        content = {
            Box(
                modifier = modifier.fillMaxWidth().height(modalScreenHeight),
                contentAlignment = Alignment.Center
            ) {
                //Bg Image
                Image(
                    modifier = modifier.fillMaxSize(),
                    alpha = 0.2F,
                    contentScale = ContentScale.Inside,
                    painter = painterResource(R.drawable.globeicon),
                    contentDescription = stringResource(R.string.SplashScreenBg)
                )

                Column(modifier = modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally)
                {
                    TextField(
                        modifier = modifier.fillMaxWidth().padding(40.dp),
                        value = userInput,
                        onValueChange = { newText -> userInput = newText},
                        label = {Text("Add New Category :")},
                        textStyle = TextStyle(fontSize = 20.sp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White.copy(alpha = 0.4F),
                            unfocusedContainerColor = Color.White.copy(alpha = 0.4F)
                        )
                    )

                    IconButton(modifier = modifier.fillMaxWidth().padding(40.dp),
                        onClick = {
                            if (validateUserInput(userInput)) {

                                // Formating string before putting to common list
                                val newCategory : String = userInput.lowercase().replaceFirstChar { it.uppercase() }

                                myMainViewModel.myCategories.addFirst(newCategory);
                                hideBottomSplashScreen()
                            } else {
                                userInput = ""
                                Toast.makeText(context, "Wrong input !", Toast.LENGTH_SHORT).show()
                            }
                        },
                        shape = RoundedCornerShape(5.dp),
                        enabled = btnState,
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color.Blue ,
                            contentColor = Color.Black,
                            disabledContainerColor =Color.Gray.copy(alpha = 0.8f) ,
                            disabledContentColor = Color.DarkGray))
                    {
                        Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(R.string.AddButton))
                    }
                }
            }
        }
    )
}

private fun validateUserInput(input: String): Boolean {
    if (input.isEmpty() or (input.length > 20)){
        return false
    }
    else if (input.toDoubleOrNull() != null){
        return false
    }
    else
        return true
}
