package com.example.expensetrackerapp.ui.screen.register

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expensetrackerapp.common.utils.showToast
import com.example.expensetrackerapp.common.widget.LoadingView
import com.example.expensetrackerapp.ui.navigation.NavigationProvider
import com.ramcosta.composedestinations.annotation.Destination


@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun RegistrationScreen(
    navigator: NavigationProvider,
    viewModel: RegistrationViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val localFocusManager = LocalFocusManager.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val registrationState by viewModel.registrationState.collectAsState()

    BackHandler {
        // Handle back navigation if needed
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Register") },
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.surface)
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(16.dp)
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTapGestures(onTap = {
                            localFocusManager.clearFocus()
                        })
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirm Password") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        // Perform registration
                        viewModel.register(email, password)
                        localFocusManager.clearFocus()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Register")
                }
            }
        }
    )

    // Handle registration state
    LaunchedEffect(registrationState) {
        when (registrationState) {
            is RegistrationState.Success -> {
                val message = (registrationState as RegistrationState.Success).successMsg
                message.let {
                    context.showToast(it)
                    navigator.navigateToLoginScreen()
                }
            }

            is RegistrationState.Error -> {
                // Show toast message
                val message = (registrationState as RegistrationState.Error).message
                context.showToast(message = message)
            }

            else -> {}
        }
    }

    if (registrationState is RegistrationState.Loading) {
        LoadingView()
    }
}



