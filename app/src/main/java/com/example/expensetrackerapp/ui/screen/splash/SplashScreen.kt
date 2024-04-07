package com.example.expensetrackerapp.ui.screen.splash

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expensetrackerapp.common.utils.showToast
import com.example.expensetrackerapp.common.widget.LoadingView
import com.example.expensetrackerapp.core.BaseUiState
import com.example.expensetrackerapp.ui.navigation.NavigationProvider
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@Destination
@Composable
fun SplashScreen(
    navigator: NavigationProvider,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val uiState by viewModel.state.collectAsState()

    // MutableState to track the current mode
    val isDarkMode = remember { mutableStateOf(false) }

    // Toggle the mode when the switch is clicked
    val toggleDarkMode: () -> Unit = {
        isDarkMode.value = !isDarkMode.value
        // Apply the theme change if needed
        if (isDarkMode.value) {
            // Apply dark theme
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            // Apply light theme
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    LaunchedEffect(uiState) {
        when (uiState) {
            is BaseUiState.Data -> {
                val resultState = (uiState as BaseUiState.Data<SplashState>).value
                when (resultState) {
                    is SplashState.LoggedIn -> {
//                        navigator.navigateToRegisterScreen()
                    }
                    SplashState.NotLoggedIn -> {
                        navigator.navigateToLoginScreen()
                    }
                }
            }
            is BaseUiState.Error -> {
                val resultState = uiState as BaseUiState.Error
                context.showToast(resultState.throwable.message ?: "An error occurred")
            }

            else -> {}
        }
    }

    if (uiState is BaseUiState.Loading) {
        LoadingView()
    }
    
    Scaffold {
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
         Button(onClick = { navigator.navigateToLoginScreen() }) {
             Text(text = "Navigate")
         }
        }
    }
}

