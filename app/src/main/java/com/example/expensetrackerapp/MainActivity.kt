package com.example.expensetrackerapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.expensetrackerapp.common.utils.showToast
import com.example.expensetrackerapp.ui.MainRoot
import com.example.expensetrackerapp.ui.theme.ExpenseTrackerAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var backPressed = 0L
    private val finish: () -> Unit = {
        if (backPressed + 3000 > System.currentTimeMillis()) {
            finishAndRemoveTask()
        } else {
            this.showToast("Press BACK again to exit")
        }
        backPressed = System.currentTimeMillis()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { false }
        setContent {
            MainRoot(finish = finish)
        }
    }
}

@Composable
fun MyApp() {
    var isDarkMode by remember { mutableStateOf(false) }

    ExpenseTrackerAppTheme(darkTheme = isDarkMode) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Greeting(name = "Android")
            Spacer(modifier = Modifier.height(16.dp))
            DarkModeSwitch(isDarkMode = isDarkMode) { newValue ->
                isDarkMode = newValue
            }
        }
    }
}

@Composable
fun DarkModeSwitch(
    isDarkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit
) {
    Switch(
        checked = isDarkMode,
        onCheckedChange = { isChecked ->
            onDarkModeChange(isChecked)
        }
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExpenseTrackerAppTheme {
        MyApp()
    }
}