package com.example.speakit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.speakit.db.repo.UserPreferenceRepo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = UserPreferenceRepo(application)

    fun setAutoPlayTranslation(enabled:  Boolean){
        viewModelScope.launch {
            repo.setAutoPlayTranslation(enabled)
        }
    }

    val sourceLanguage: StateFlow<String?> = repo.sourceLanguage
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val targetLanguage: StateFlow<String?> = repo.targetLanguage
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun setSourceLanguage(code: String) {
        viewModelScope.launch {
            repo.setSourceLanguage(code)
        }
    }

    fun setTargetLanguage(code: String) {
        viewModelScope.launch {
            repo.setTargetLanguage(code)
        }
    }
}