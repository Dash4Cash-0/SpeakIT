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

    val playbackSpeed: StateFlow<Float> = repo.playbackSpeed
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 1f)
    val autoPlayTranslation: StateFlow<Boolean> = repo.autoPlayTranslation
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), true)
    val autoSaveSentence: StateFlow<Boolean> = repo.autoSaveSentence
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)
    val suggestForms: StateFlow<Boolean> = repo.suggestForms
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)
    val dailyPracticeReminder: StateFlow<Boolean> = repo.dailyPracticeReminder
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), true)

    fun setPlaybackSpeed(speed: Float) {
        viewModelScope.launch {
            repo.setPlaybackSpeed(speed)
        }
    }

    fun setAutoPlayTranslation(enabled:  Boolean){
        viewModelScope.launch {
            repo.setAutoPlayTranslation(enabled)
        }
    }

    fun setAutoSaveSentence(enabled:  Boolean){
        viewModelScope.launch {
            repo.setAutoSaveSentence(enabled)
        }
    }
    fun setSuggestForms(enabled:  Boolean){
        viewModelScope.launch {
            repo.setSuggestForms(enabled)
        }
    }
    fun setDailyPracticeReminder(enabled:  Boolean){
        viewModelScope.launch {
            repo.setDailyPracticeReminder(enabled)
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