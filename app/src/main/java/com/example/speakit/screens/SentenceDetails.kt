package com.example.speakit.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.speakit.ui.theme.SpeakITTheme
import com.example.speakit.viewmodel.SentenceDetailViewModel

@Composable
fun SentenceDetails(
    sentenceId: Long,
    onBack: () -> Unit,
    viewModel: SentenceDetailViewModel = viewModel()
){

    SentenceDetailsContent(

    )
}

@Composable
fun SentenceDetailsContent(){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) { }
    }


}



@Preview(showBackground = true, apiLevel = 36, name = "Library preview")
@Composable
fun SentenceDetailsPreview(){
    SpeakITTheme{
        Scaffold(
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)){
                SentenceDetailsContent(

                )
            }
        }
    }
}
