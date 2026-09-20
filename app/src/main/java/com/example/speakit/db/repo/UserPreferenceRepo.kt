package com.example.speakit.db.repo

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
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