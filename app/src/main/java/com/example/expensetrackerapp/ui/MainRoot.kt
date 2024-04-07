package com.example.expensetrackerapp.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.expensetrackerapp.ui.navigation.AppNavigationProvider
import com.example.expensetrackerapp.ui.screen.NavGraphs
import com.example.expensetrackerapp.ui.screen.destinations.RegistrationScreenDestination
import com.example.expensetrackerapp.ui.theme.ExpenseTrackerAppTheme
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.dependency

@Composable
fun MainRoot(
    finish: () -> Unit
) {
    val navController = rememberNavController()
    val isDarkMode = isSystemInDarkTheme()

    val currentBackStackEntryAsState by navController.currentBackStackEntryAsState()
    val destination = currentBackStackEntryAsState?.destination?.route
        ?: NavGraphs.root.route

    BackHandler {
       finish.invoke()
    }
    ExpenseTrackerAppTheme(darkTheme = isDarkMode) {
        SetupSystemUi(
            systemUiController = rememberSystemUiController(),
            systemColor = MaterialTheme.colorScheme.primary
        )
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            DestinationsNavHost(
                navController = navController,
                navGraph = NavGraphs.root,
                dependenciesContainerBuilder = {
                    dependency(AppNavigationProvider(destinationsNavigator, navController))
                }
            ) {

            }
        }
    }
}

@Composable
fun SetupSystemUi(
    systemUiController: SystemUiController,
    systemColor: Color
) {
    SideEffect {
        systemUiController.setSystemBarsColor(color = systemColor)
    }
}