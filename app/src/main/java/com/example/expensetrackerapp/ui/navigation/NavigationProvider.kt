package com.example.expensetrackerapp.ui.navigation

import com.ramcosta.composedestinations.spec.Direction

interface NavigationProvider {
    fun navigateUp()
    fun navigateToRegisterScreen()
    fun navigateToLoginScreen()
}
