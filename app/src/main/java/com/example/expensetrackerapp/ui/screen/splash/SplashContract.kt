package com.example.expensetrackerapp.ui.screen.splash

import com.example.expensetrackerapp.data.model.LoginStatus

data class ResultState(
    val loginState: LoginStatus = LoginStatus.NONE

)

sealed class Event {

}