package com.example.speakit.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.speakit.components.LanguageSettingRow
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
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(text = "Settings",
            modifier = Modifier.padding(6.dp),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold)
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ) {
            LanguageSettingRow(
                label = "\uD83C\uDDEC\uD83C\uDDE7",
                language = "English"
            ) { }
            LanguageSettingRow(
                label = "\uD83C\uDDEE\uD83C\uDDF9",
                language = "Italian"
            ) { }

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