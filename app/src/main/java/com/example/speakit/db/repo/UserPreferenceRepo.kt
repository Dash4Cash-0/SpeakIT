package com.example.speakit.db.repo

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

const val PREFERENCES_NAME = "my_preferences"

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class UserPreferenceRepo(private val context: Context) {

    private object Keys {
        val SOURCE_LANGUAGE = stringPreferencesKey("source_language")
        val TARGET_LANGUAGE = stringPreferencesKey("target_language")
        val AUTO_PLAY = booleanPreferencesKey("auto_play")
        val SAVE_SENTENCE = booleanPreferencesKey("save_sentence")
        val SUGGEST_FORMS = booleanPreferencesKey("suggest_forms")
        val DAILY_PRACTICE_REMINDER = booleanPreferencesKey("daily_practice_reminder")
        val PLAYBACK_SPEED = floatPreferencesKey("playback_speed")
        val CATEGORY_CHOICE = stringPreferencesKey("category_choice")
    }

    val playbackSpeed: Flow<Float> = context.dataStore.data
        .map { it[Keys.PLAYBACK_SPEED] ?: 1f }
    val autoPlayTranslation: Flow<Boolean> = context.dataStore.data
        .map { it[Keys.AUTO_PLAY] ?: true }
    val autoSaveSentence: Flow<Boolean> = context.dataStore.data
        .map { it[Keys.SAVE_SENTENCE] ?: false }
    val suggestForms: Flow<Boolean> = context.dataStore.data
        .map { it[Keys.SUGGEST_FORMS] ?: false }
    val dailyPracticeReminder: Flow<Boolean> = context.dataStore.data
        .map { it[Keys.DAILY_PRACTICE_REMINDER] ?: true }
    val categoryChoice: Flow<String> = context.dataStore.data
        .map { it[Keys.CATEGORY_CHOICE] ?: "All"}
    suspend fun setPlaybackSpeed(speed: Float) {
        context.dataStore.edit { it[Keys.PLAYBACK_SPEED] = speed }
    }
    suspend fun setAutoPlayTranslation(enabled: Boolean) {
        context.dataStore.edit { it[Keys.AUTO_PLAY] = enabled }
    }

    suspend fun setAutoSaveSentence(enabled: Boolean) {
        context.dataStore.edit { it[Keys.SAVE_SENTENCE] = enabled }
    }

    suspend fun setSuggestForms(enabled: Boolean) {
        context.dataStore.edit { it[Keys.SUGGEST_FORMS] = enabled }
    }

    suspend fun setDailyPracticeReminder(enabled: Boolean) {
        context.dataStore.edit { it[Keys.DAILY_PRACTICE_REMINDER] = enabled }
    }
    suspend fun setCategoryChoice(category: String) {
        context.dataStore.edit { it[Keys.CATEGORY_CHOICE] = category }
    }

    val sourceLanguage: Flow<String?> = context.dataStore.data
        .map { it[Keys.SOURCE_LANGUAGE] }

    val targetLanguage: Flow<String?> = context.dataStore.data
        .map { it[Keys.TARGET_LANGUAGE] }

    suspend fun setSourceLanguage(code: String) {
        context.dataStore.edit { it[Keys.SOURCE_LANGUAGE] = code }
    }

    suspend fun setTargetLanguage(code: String) {
        context.dataStore.edit { it[Keys.TARGET_LANGUAGE] = code }
    }
}