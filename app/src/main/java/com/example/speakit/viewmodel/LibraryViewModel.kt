package com.example.speakit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.speakit.db.repo.UserPreferenceRepo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LibraryViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = UserPreferenceRepo(application)

    val categoryChoice: StateFlow<Boolean> = repo.categoryChoice
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    fun setCategoryChoice(enabled: Boolean){
        viewModelScope.launch {
            repo.setCategoryChoice(enabled)
        }
    }
}