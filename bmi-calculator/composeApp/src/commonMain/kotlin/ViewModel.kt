package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import model.BmiCategory
import model.BmiModel
import model.Gender

class BmiViewModel {
    var uiState by mutableStateOf(BmiModel())
        private set

    var currentScreen by mutableStateOf(Screen.GENDER)
        private set

    fun setGender(gender: Gender) {
        uiState = uiState.copy(gender = gender)
    }

    fun setHeight(heightCm: Double) {
        uiState = uiState.copy(heightCm = heightCm)
    }

    fun setWeight(weightKg: Double) {
        uiState = uiState.copy(weightKg = weightKg)
    }

    fun calculateBmi() {
        val heightInMeters = uiState.heightCm / 100
        val bmiValue = uiState.weightKg / (heightInMeters * heightInMeters)
        val category = BmiCategory.fromBmi(bmiValue)

        uiState = uiState.copy(
            bmiValue = bmiValue,
            bmiCategory = category
        )
    }

    fun navigateToNextScreen() {
        currentScreen = when (currentScreen) {
            Screen.GENDER -> Screen.MEASUREMENTS
            Screen.MEASUREMENTS -> {
                calculateBmi()
                Screen.RESULTS
            }
            Screen.RESULTS -> Screen.GENDER // Reset to start
        }
    }

    fun navigateToPreviousScreen() {
        currentScreen = when (currentScreen) {
            Screen.GENDER -> Screen.GENDER
            Screen.MEASUREMENTS -> Screen.GENDER
            Screen.RESULTS -> Screen.MEASUREMENTS
        }
    }

    fun resetCalculator() {
        uiState = BmiModel()
        currentScreen = Screen.GENDER
    }

    enum class Screen {
        GENDER, MEASUREMENTS, RESULTS
    }
}