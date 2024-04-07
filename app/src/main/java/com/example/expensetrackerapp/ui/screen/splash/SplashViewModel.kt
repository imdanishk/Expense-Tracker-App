package com.example.expensetrackerapp.ui.screen.splash

import com.example.expensetrackerapp.core.BaseViewModel
import com.example.expensetrackerapp.data.model.LoginStatus
import com.example.expensetrackerapp.domain.usecase.GetLoginStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getLoginStatusUseCase: GetLoginStatusUseCase
) : BaseViewModel<SplashState>() {

    init {
        readOnBoardingState()
    }

    private fun readOnBoardingState() {
        launchViewModelScope {
            setLoading(true)
            getLoginStatusUseCase().collect { loginStatus ->
                val state = if (loginStatus == LoginStatus.LOGGED_IN.value) {
                    SplashState.LoggedIn(LoginStatus.LOGGED_IN)
                } else {
                    SplashState.NotLoggedIn
                }
                setData(state)
                setLoading(false)
            }
        }
    }

    fun clearState() {
        clearError()
    }
}


sealed class SplashState {
    data class LoggedIn(val loginState: LoginStatus) : SplashState()
    data object NotLoggedIn : SplashState()
}
