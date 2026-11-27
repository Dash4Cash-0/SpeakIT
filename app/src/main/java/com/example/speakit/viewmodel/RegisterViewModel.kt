package com.example.speakit.viewmodel

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.speakit.R
import com.example.speakit.network.registerNewUser
import io.github.jan.supabase.auth.exception.AuthErrorCode
import io.github.jan.supabase.auth.exception.AuthRestException
import io.github.jan.supabase.exceptions.HttpRequestException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    sealed interface RegisterUiState {
        data object Idle : RegisterUiState
        data object Loading : RegisterUiState
        data object Success : RegisterUiState
        data class Error(@StringRes val messageRes: Int) : RegisterUiState
    }

    private val _uiState = MutableStateFlow<RegisterUiState>(RegisterUiState.Idle)
    val uiState: StateFlow<RegisterUiState> = _uiState

    fun registerUser(name: String, email: String, password: String) {
        if (_uiState.value is RegisterUiState.Loading) return

        viewModelScope.launch {
            _uiState.value = RegisterUiState.Loading
            try {
                registerNewUser(userName = name, userEmail = email, userPassword = password)
                _uiState.value = RegisterUiState.Success
            } catch (e: CancellationException) {
                throw e
            } catch (e: AuthRestException) {
                _uiState.value = RegisterUiState.Error(messageFor(e.errorCode))
            } catch (e: HttpRequestException) {
                _uiState.value = RegisterUiState.Error(R.string.register_errorNetwork)
            } catch (e: Exception) {
                _uiState.value = RegisterUiState.Error(R.string.register_errorUnknown)
            }
        }
    }

    @StringRes
    private fun messageFor(code: AuthErrorCode?): Int = when (code) {
        AuthErrorCode.WeakPassword -> R.string.register_errorWeakPassword
        AuthErrorCode.EmailAddressInvalid,
        AuthErrorCode.ValidationFailed -> R.string.register_errorInvalidEmail
        AuthErrorCode.UserAlreadyExists,
        AuthErrorCode.EmailExists -> R.string.register_errorEmailExists
        AuthErrorCode.OverEmailSendRateLimit,
        AuthErrorCode.OverRequestRateLimit -> R.string.register_errorRateLimit
        AuthErrorCode.SignupDisabled,
        AuthErrorCode.EmailProviderDisabled -> R.string.register_errorSignupDisabled
        else -> R.string.register_errorUnknown
    }
}
