package com.example.speakit.screens

import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.speakit.R
import com.example.speakit.ui.theme.SpeakITTheme
import com.example.speakit.viewmodel.LoginViewModel

@Composable
fun Login(
    viewModel: LoginViewModel = viewModel()
){

    LoginContent(

    )
}

@Composable
fun LoginContent(){

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = stringResource(R.string.app_name), fontSize = 15.sp, fontWeight = FontWeight.Bold)

            Text("English · " +
                    "Português · " +
                    "Italiano · " +
                    "Español · " +
                    "Deutsch · " +
                    "Français · " +
                    "Nederlands · " +
                    "Svenska · " +
                    "日本語 · " +
                    "한국어 · " +
                    "中文（普通话)",
                fontSize = 11.sp,
                maxLines = 1,
                modifier = Modifier.width(120.dp).basicMarquee(
                        iterations = Int.MAX_VALUE,
                        velocity = 20.dp,
                        repeatDelayMillis = 0,
                        spacing = MarqueeSpacing(16.dp)
                    ))
        }
    }



}




@Preview(showBackground = true, apiLevel = 36, name = "Library preview")
@Composable
fun LoginPreview(){
    SpeakITTheme{
        Scaffold(
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)){
                LoginContent(

                )
            }
        }
    }
}