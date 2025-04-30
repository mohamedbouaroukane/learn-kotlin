package org.welledge.project.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.welledge.project.domain.model.ChartMode
import org.welledge.project.domain.model.ChartType
import org.welledge.project.domain.model.Equation
import org.welledge.project.domain.usecase.GenerateChartUseCase

class ChartViewModel(private val generateChartUseCase: GenerateChartUseCase) : ViewModel() {
    var input by mutableStateOf(Equation())
        private set

    var chartType by mutableStateOf(ChartType.SURFACE)
        private set

    var color by mutableStateOf(Color.Blue)
        private set

    var chartMode by mutableStateOf(ChartMode.EQUATION)
        private set

    var generateChartTrigger by mutableStateOf(false)
        private set

    init {
        // Observe flows from the use case
        generateChartUseCase.equation.onEach { input = it }.launchIn(viewModelScope)
        generateChartUseCase.chartType.onEach { chartType = it }.launchIn(viewModelScope)
        generateChartUseCase.color.onEach { color = it }.launchIn(viewModelScope)
        generateChartUseCase.chartMode.onEach { chartMode = it }.launchIn(viewModelScope)
        generateChartUseCase.generateTrigger.onEach { generateChartTrigger = it }.launchIn(viewModelScope)
    }

    fun updateEquation(equation: String) {
        generateChartUseCase.updateEquation(equation)
    }

    fun updateXRange(start: Double, end: Double) {
        generateChartUseCase.updateXRange(start, end)
    }

    fun updateYRange(start: Double, end: Double) {
        generateChartUseCase.updateYRange(start, end)
    }

    fun updateChartType(type: ChartType) {
        generateChartUseCase.updateChartType(type)
    }

    fun updateColor(color: Color) {
        generateChartUseCase.updateColor(color)
    }

    fun triggerGenerateChart() {
        generateChartUseCase.generateChart()
    }
}