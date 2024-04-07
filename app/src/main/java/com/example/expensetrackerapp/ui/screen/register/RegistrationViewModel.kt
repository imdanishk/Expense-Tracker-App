package com.example.expensetrackerapp.ui.screen.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetrackerapp.domain.usecase.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    private val _registrationState = MutableStateFlow<RegistrationState>(RegistrationState.Initial)
    val registrationState: StateFlow<RegistrationState> = _registrationState

    fun register(email: String, password: String) {
        viewModelScope.launch {
            _registrationState.value = RegistrationState.Loading
            try {
                registerUserUseCase(email, password)
                _registrationState.value = RegistrationState.Success("Verify email")
            } catch (e: Exception) {
                _registrationState.value =
                    RegistrationState.Error(e.message ?: "Registration failed")
            }
        }
    }
}

sealed class RegistrationState {
    data object Initial : RegistrationState()
    data object Loading : RegistrationState()
    data class Success(val successMsg: String) : RegistrationState()
    data class Error(val message: String) : RegistrationState()
}
