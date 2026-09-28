package com.example.speakit

import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isToggleable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.speakit.screens.SettingsContent
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SettingsContentTest {

    // Gives each test an empty screen to put composables on
    @get:Rule
    val composeRule = createComposeRule()

    // Finds the toggleable settings row that contains the given label
    private fun switchFor(label: String) =
        composeRule.onNode(isToggleable() and hasText(label))

    @Test
    fun switches_showTheValuesPassedIn() {
        // Arrange
        composeRule.setContent {
            SettingsContent(
                sourceLanguage = "English",
                targetLanguage = "Italian",
                autoPlayTranslation = true,
                autoSaveSentence = false,
                suggestForms = false,
                dailyPracticeReminder = true,
                onAutoPlaySwitch = {},
                onAutoSaveSentenceSwitch = {},
                onSuggestFormsSwitch = {},
                onDailyPracticeSwitch = {},
                onSourceLanguageChange = {},
                onTargetLanguageChange = {},
                onPlaybackSpeedClick = {}
            )
        }

        // Assert
        switchFor("Auto-play translation").assertIsOn()
        switchFor("Save every sentence").assertIsOff()
        switchFor("Suggest related forms").assertIsOff()
        switchFor("Daily practice reminder").assertIsOn()
    }

    @Test
    fun clickingAutoSaveSwitch_sendsTrueToCallback() {
        // Arrange
        var autoSaveReceived: Boolean? = null
        var autoPlayReceived: Boolean? = null
        composeRule.setContent {
            SettingsContent(
                sourceLanguage = "English",
                targetLanguage = "Italian",
                autoPlayTranslation = true,
                autoSaveSentence = false,
                suggestForms = false,
                dailyPracticeReminder = true,
                onAutoPlaySwitch = {autoPlayReceived = it},
                onAutoSaveSentenceSwitch = { autoSaveReceived = it },
                onSuggestFormsSwitch = {},
                onDailyPracticeSwitch = {},
                onSourceLanguageChange = {},
                onTargetLanguageChange = {},
                onPlaybackSpeedClick = {}
            )
        }

        switchFor("Save every sentence").performClick()
        switchFor("Auto-play translation").performClick()

        assertEquals(true, autoSaveReceived)
        assertEquals(false, autoPlayReceived)

    }
}