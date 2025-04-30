package org.welledge.project.data.repository

import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.welledge.project.domain.model.ChartMode
import org.welledge.project.domain.model.ChartType
import org.welledge.project.domain.model.Equation
import org.welledge.project.domain.repository.ChartRepository

class ChartRepositoryImpl : ChartRepository {
    private val _equationFlow = MutableStateFlow(Equation())
    override val equationFlow: StateFlow<Equation> = _equationFlow

    private val _chartTypeFlow = MutableStateFlow(ChartType.SURFACE)
    override val chartTypeFlow: StateFlow<ChartType> = _chartTypeFlow

    private val _colorFlow = MutableStateFlow(Color.Blue)
    override val colorFlow: StateFlow<Color> = _colorFlow

    private val _chartModeFlow = MutableStateFlow(ChartMode.EQUATION)
    override val chartModeFlow: StateFlow<ChartMode> = _chartModeFlow

    private val _generateTriggerFlow = MutableStateFlow(false)
    override val generateTriggerFlow: StateFlow<Boolean> = _generateTriggerFlow

    override fun updateEquation(equation: String) {
        _equationFlow.value = _equationFlow.value.copy(equation = equation)
    }

    override fun updateXRange(start: Double, end: Double) {
        _equationFlow.value = _equationFlow.value.copy(xRange = start..end)
    }

    override fun updateYRange(start: Double, end: Double) {
        _equationFlow.value = _equationFlow.value.copy(yRange = start..end)
    }

    override fun updateChartType(type: ChartType) {
        _chartTypeFlow.value = type
    }

    override fun updateColor(color: Color) {
        _colorFlow.value = color
    }

    override fun updateChartMode(mode: ChartMode) {
        _chartModeFlow.value = mode
    }

    override fun triggerGenerate() {
        _generateTriggerFlow.value = !_generateTriggerFlow.value
    }
}