package com.example.speakit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.speakit.network.registerNewUser
import kotlinx.coroutines.launch

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    fun registerUser(name: String, email: String, password: String){
        viewModelScope.launch {
            registerNewUser(userEmail = email, userPassword = password)
        }

    }
}