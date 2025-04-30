package org.welledge.project

import ChartScreen
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.koin.core.context.startKoin
import org.welledge.project.di.appModule

fun main() {
    // Initialize Koin
    startKoin {
        modules(appModule)
    }

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "3D Math Chart Generator",
            state = rememberWindowState(
                placement = WindowPlacement.Maximized
            )
        ) {
            MaterialTheme {
                ChartScreen()
            }
        }
    }
}