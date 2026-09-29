package com.example.speakit.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.speakit.components.ChangeSourceLanguageDialog
import com.example.speakit.components.LanguageSettingRow
import com.example.speakit.navigation.BottomNavbar
import com.example.speakit.ui.theme.SpeakITTheme
import com.example.speakit.viewmodel.SettingsViewModel


@Composable
fun Settings(viewModel: SettingsViewModel = viewModel()) {

    val sourceLanguage by viewModel.sourceLanguage.collectAsState()
    val targetLanguage by viewModel.targetLanguage.collectAsState()
    val autoPlayTranslation by viewModel.autoPlayTranslation.collectAsState()
    val autoSaveSentence by viewModel.autoSaveSentence.collectAsState()
    val suggestForms by viewModel.suggestForms.collectAsState()
    val dailyPracticeReminder by viewModel.dailyPracticeReminder.collectAsState()

    SettingsContent(
        sourceLanguage = sourceLanguage,
        targetLanguage = targetLanguage,
        autoPlayTranslation = autoPlayTranslation,
        autoSaveSentence = autoSaveSentence,
        suggestForms = suggestForms,
        dailyPracticeReminder = dailyPracticeReminder,
        onAutoPlaySwitch = viewModel::setAutoPlayTranslation,
        onAutoSaveSentenceSwitch = viewModel::setAutoSaveSentence,
        onSuggestFormsSwitch = viewModel::setSuggestForms,
        onDailyPracticeSwitch = viewModel::setDailyPracticeReminder,
        onSourceLanguageChange = viewModel::setSourceLanguage,
        onTargetLanguageChange = viewModel::setTargetLanguage,
        onPlaybackSpeedClick = {

        }
    )

}

@Composable
fun SettingsContent(
    sourceLanguage: String?,
    targetLanguage: String?,
    autoPlayTranslation: Boolean,
    autoSaveSentence: Boolean,
    suggestForms: Boolean,
    dailyPracticeReminder: Boolean,
    onAutoPlaySwitch: (Boolean) -> Unit,
    onAutoSaveSentenceSwitch: (Boolean) -> Unit,
    onSuggestFormsSwitch: (Boolean) -> Unit,
    onDailyPracticeSwitch: (Boolean) -> Unit,
    onSourceLanguageChange: (String) -> Unit,
    onTargetLanguageChange: (String) -> Unit,
    onPlaybackSpeedClick: () -> Unit
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
            var showDialog by remember { mutableStateOf(false) }
            LanguageSettingRow(
                label = "\uD83C\uDDEC\uD83C\uDDE7",
                language = sourceLanguage,
                onChangeClick = { showDialog = true}
            )
            if (showDialog)
            {
                ChangeSourceLanguageDialog(
                    onDismiss = {showDialog = false},
                    onSelect = {lang ->
                        onTargetLanguageChange(lang)
                        showDialog = false})
            }
            LanguageSettingRow(
                label = "\uD83C\uDDEE\uD83C\uDDF9",
                language = targetLanguage
            ) {}

        }
        ElevatedCard(
            modifier = Modifier.padding(top = 10.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ) {
            Text(text = "PLAYBACK SPEED",
                modifier = Modifier
                    .padding(top = 12.dp, start = 15.dp),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp)
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                SpeedButtons("0.5x")
                SpeedButtons("0.7x")
                SpeedButtons("1x")
                SpeedButtons("1.3x")
            }
        }

        ElevatedCard(
            modifier = Modifier.padding(top = 10.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ) {
            SettingsSwitches("Auto-play translation", autoPlayTranslation, onAutoPlaySwitch )
            SettingsSwitches("Save every sentence", autoSaveSentence, onAutoSaveSentenceSwitch)
            SettingsSwitches("Suggest related forms", suggestForms, onSuggestFormsSwitch)
            SettingsSwitches("Daily practice reminder", dailyPracticeReminder, onDailyPracticeSwitch)

        }
    }

}

@Composable
private fun SpeedButtons(label: String){
    TextButton(
        modifier = Modifier.padding(vertical = 8.dp),
        onClick = {}
    ) {
        Text(text = label)
    }
}

@Composable
private fun SettingsSwitches(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .toggleable(
                value = checked,
                role = Role.Switch,
                onValueChange = onCheckedChange
            )
            .padding(10.dp)
    ) {
        Text(text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.ExtraBold)
        Switch(
            checked = checked,
            colors = SwitchDefaults.colors(
                checkedTrackColor = Color.Red,
                uncheckedTrackColor = Color.Gray,
                uncheckedThumbColor = Color.White
            ),
            onCheckedChange = null
        )
    }
}

@Preview(showBackground = true, apiLevel = 36, name = "Settings preview")
@Composable
fun SettingsPreview(){
    SpeakITTheme{
        Scaffold(
            bottomBar = {BottomNavbar(rememberNavController())}
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)){
                SettingsContent(
                    sourceLanguage = "English",
                    targetLanguage = "Italian",
                    autoPlayTranslation = true,
                    autoSaveSentence = false,
                    suggestForms = false,
                    dailyPracticeReminder = true,
                    onSourceLanguageChange = {},
                    onTargetLanguageChange = {},
                    onPlaybackSpeedClick = {},
                    onAutoPlaySwitch = {},
                    onAutoSaveSentenceSwitch = {},
                    onSuggestFormsSwitch = {},
                    onDailyPracticeSwitch = {}

                )
            }
        }
    }
}
