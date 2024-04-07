package com.example.expensetrackerapp.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetrackerapp.common.utils.FirebaseManager
import com.example.expensetrackerapp.domain.usecase.LoginUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Initial)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(email: String, password: String) {

        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            try {
                if (FirebaseManager().isEmailVerified()) {
                    loginUserUseCase(email, password)
                    _loginState.value = LoginState.Success("Login successful")
                } else {
                    _loginState.value = LoginState.Success("Verify email")
                }
            } catch (e: Exception) {
                _loginState.value = LoginState.Error(e.message ?: "Login failed")
            }
        }
    }
}

sealed class LoginState {
    data object Initial : LoginState()
    data object Loading : LoginState()
    data class Success(val successMsg: String) : LoginState()
    data class Error(val message: String) : LoginState()
}
