package com.example.speakit.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.speakit.components.SettingsCard
import com.example.speakit.ui.theme.SpeakITTheme
import com.example.speakit.viewmodel.SettingsViewModel


@Composable
fun Settings(viewModel: SettingsViewModel = viewModel()) {

    val sourceLanguage by viewModel.sourceLanguage.collectAsState()
    val targetLanguage by viewModel.targetLanguage.collectAsState()

    SettingsContent(
        sourceLanguage = sourceLanguage,
        targetLanguage = targetLanguage,
        onSourceLanguageChange = viewModel::setSourceLanguage,
        onTargetLanguageChange = viewModel::setTargetLanguage
    )

}

@Composable
fun SettingsContent(
    sourceLanguage: String?,
    targetLanguage: String?,
    onSourceLanguageChange: (String) -> Unit,
    onTargetLanguageChange: (String) -> Unit
){
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ) {
            Text("TEST")
            SettingsCard(label = "Source language: ${sourceLanguage ?: "Not set"}")
            SettingsCard(label = "Target language: ${targetLanguage ?: "Not set"}")
        }
    }

}

@Preview(showBackground = true, apiLevel = 36, name = "Settings preview")
@Composable
fun SettingsPreview(){
    SpeakITTheme(
    ) {
        SettingsContent(
            sourceLanguage = "English",
            targetLanguage = "Italian",
            onSourceLanguageChange = {},
            onTargetLanguageChange = {}
        )
    }

}