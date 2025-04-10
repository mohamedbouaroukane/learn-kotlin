package org.hoods.weather

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.hoods.weather.ui.components.getNavigationType
import org.hoods.weather.ui.home.HomeScreen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import weather.composeapp.generated.resources.Res
import weather.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App(windowWidthSizeClass: WindowWidthSizeClass) {
    MaterialTheme {
            HomeScreen(
                navigationType = getNavigationType(windowWidthSizeClass),

            )
        }
    }
