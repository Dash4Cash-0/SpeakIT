package com.example.speakit.viewmodel

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.speakit.R
import com.example.speakit.network.loginUser
import io.github.jan.supabase.auth.exception.AuthErrorCode
import io.github.jan.supabase.auth.exception.AuthRestException
import io.github.jan.supabase.exceptions.HttpRequestException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    sealed interface LoginUiState {
        data object Idle : LoginUiState
        data object Loading : LoginUiState
        data object Success : LoginUiState
        data class Error(@StringRes val messageRes: Int) : LoginUiState
    }

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)

    val uiState: StateFlow<LoginUiState> = _uiState

    fun loginCurrentUser(userEmail: String, userPassword: String) {

        if (_uiState.value is LoginUiState.Loading) return

        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading
            try {
                loginUser(userEmail = userEmail, userPassword = userPassword)
                _uiState.value = LoginUiState.Success
            } catch (e: CancellationException) {
                throw e
            } catch (e: AuthRestException) {
                _uiState.value = LoginUiState.Error(messageFor(e.errorCode))
            } catch (e: HttpRequestException) {
                _uiState.value = LoginUiState.Error(R.string.login_errorNetwork)
            } catch (e: Exception) {
                _uiState.value = LoginUiState.Error(R.string.login_errorUnknown)
            }
        }

    }

    @StringRes
    private fun messageFor(code: AuthErrorCode?): Int = when (code) {
        AuthErrorCode.InvalidCredentials -> R.string.login_errorInvalidCredentials
        AuthErrorCode.EmailNotConfirmed -> R.string.login_errorNotConfirmed
        AuthErrorCode.OverRequestRateLimit -> R.string.login_errorRateLimit
        else -> R.string.login_errorUnknown
    }

}