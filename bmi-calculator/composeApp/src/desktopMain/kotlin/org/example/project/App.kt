package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import bmi.composeapp.generated.resources.Res
import bmi.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.theme.BMITheme
import viewmodel.BmiViewModel
import viewmodel.ui.GenderScreen
import viewmodel.ui.MeasurementsScreen
import viewmodel.ui.ResultScreen

@Composable
@Preview
fun App() {
    val viewModel = remember { BmiViewModel() }

    MaterialTheme {
        when (viewModel.currentScreen) {
            BmiViewModel.Screen.GENDER -> GenderScreen(viewModel)
            BmiViewModel.Screen.MEASUREMENTS -> MeasurementsScreen(viewModel)
            BmiViewModel.Screen.RESULTS -> ResultScreen(viewModel)
        }
    }
}