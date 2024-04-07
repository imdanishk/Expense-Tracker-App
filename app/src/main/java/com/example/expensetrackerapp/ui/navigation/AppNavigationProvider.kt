package com.example.expensetrackerapp.ui.navigation

import androidx.navigation.NavHostController
import com.example.expensetrackerapp.ui.screen.destinations.LoginScreenDestination
import com.example.expensetrackerapp.ui.screen.destinations.RegistrationScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

class AppNavigationProvider(
    private val destinationNavigator: DestinationsNavigator,
    private val navController: NavHostController,
) : NavigationProvider {

    override fun navigateUp() {
        destinationNavigator.navigateUp()
    }

    override fun navigateToRegisterScreen() {
        destinationNavigator.navigate(RegistrationScreenDestination)
    }

    override fun navigateToLoginScreen() {
        destinationNavigator.navigate(LoginScreenDestination)
    }

}