package com.example.speakit.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
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


}
