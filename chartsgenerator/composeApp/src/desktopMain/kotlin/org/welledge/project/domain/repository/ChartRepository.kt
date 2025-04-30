package org.welledge.project.domain.repository

import androidx.compose.ui.graphics.Color
import org.welledge.project.domain.model.ChartMode
import org.welledge.project.domain.model.ChartType
import org.welledge.project.domain.model.Equation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface ChartRepository {
    val equationFlow: StateFlow<Equation>
    val chartTypeFlow: StateFlow<ChartType>
    val colorFlow: StateFlow<Color>
    val chartModeFlow: StateFlow<ChartMode>
    val generateTriggerFlow: StateFlow<Boolean>

    fun updateEquation(equation: String)
    fun updateXRange(start: Double, end: Double)
    fun updateYRange(start: Double, end: Double)
    fun updateChartType(type: ChartType)
    fun updateColor(color: Color)
    fun updateChartMode(mode: ChartMode)
    fun triggerGenerate()
}