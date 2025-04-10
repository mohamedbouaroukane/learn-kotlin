package org.hoods.weather

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.hoods.weather.di.initKoin

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun main(){
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Weather",
        ) {
            val calculatedScreenSize = calculateWindowSizeClass()
            App(calculatedScreenSize.widthSizeClass)
        }
    }
}