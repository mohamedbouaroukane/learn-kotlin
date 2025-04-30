package org.welledge.project.domain.usecase

import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.StateFlow
import org.welledge.project.domain.model.ChartMode
import org.welledge.project.domain.model.ChartType
import org.welledge.project.domain.model.Equation
import org.welledge.project.domain.repository.ChartRepository

class GenerateChartUseCase(private val repository: ChartRepository) {
    val equation: StateFlow<Equation> = repository.equationFlow
    val chartType: StateFlow<ChartType> = repository.chartTypeFlow
    val color: StateFlow<Color> = repository.colorFlow
    val chartMode: StateFlow<ChartMode> = repository.chartModeFlow
    val generateTrigger: StateFlow<Boolean> = repository.generateTriggerFlow

    fun updateEquation(equation: String) = repository.updateEquation(equation)
    fun updateXRange(start: Double, end: Double) = repository.updateXRange(start, end)
    fun updateYRange(start: Double, end: Double) = repository.updateYRange(start, end)
    fun updateChartType(type: ChartType) = repository.updateChartType(type)
    fun updateColor(color: Color) = repository.updateColor(color)
    fun generateChart() = repository.triggerGenerate()
}