package org.welledge.project.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import org.jzy3d.plot3d.builder.SurfaceBuilder
import org.jzy3d.chart.Chart
import org.jzy3d.chart.controllers.thread.camera.CameraThreadController
import org.jzy3d.chart.factories.EmulGLChartFactory
import org.jzy3d.chart.factories.IChartFactory
import org.jzy3d.colors.ColorMapper
import org.jzy3d.colors.colormaps.ColorMapRainbow
import org.jzy3d.maths.Coord3d
import org.jzy3d.colors.Color as JzyColor
import org.jzy3d.maths.Range
import org.jzy3d.plot3d.builder.*
import org.jzy3d.plot3d.builder.concrete.OrthonormalGrid
import org.jzy3d.plot3d.primitives.LineStrip
import org.jzy3d.plot3d.primitives.Scatter
import org.jzy3d.plot3d.rendering.canvas.Quality
import org.jzy3d.plot3d.transform.squarifier.XZSquarifier
import org.welledge.project.domain.model.ChartMode
import org.welledge.project.domain.model.ChartType
import org.welledge.project.domain.model.Equation
import org.welledge.project.util.ExpressionEvaluator


@Composable
fun Jzy3DChart(
    equation: Equation,
    chartType: ChartType,
    mainColor: Color,
    chartMode: ChartMode,
    trigger: Boolean
) {
    // Create a key that changes whenever any parameter changes
    val chartKey = "${equation.hashCode()}-${chartType.name}-${mainColor.hashCode()}-${chartMode.name}-$trigger"
    print(chartKey)
    // Store the chart in a mutable state
    var chartState by remember { mutableStateOf<Chart?>(createEquationChart(equation, chartType, mainColor)) }

    // Create a new chart whenever the key changes
    LaunchedEffect(chartKey) {
        // Dispose of the old chart if it exists
        chartState?.dispose()

        // Create a new chart based on the current parameters
        chartState = createEquationChart(equation, chartType, mainColor)
    }

    // Clean up on dispose
    DisposableEffect(Unit) {
        onDispose {
            chartState?.dispose()
        }
    }

    // Render the chart if it exists
    chartState?.canvas?.let { canvas ->
        key(chartKey) {
            Box {
                SwingPanel(
                    factory = { canvas as java.awt.Component },
                    modifier = Modifier.width(500.dp).height(500.dp).zIndex(0f),
                    update = { /* تحديث اختياري */ }
                )
            }
        }
    }
}
fun createTrajectoryChart(): Chart {
    val points = mutableListOf<Coord3d>()
    val total = 1000
    for (i in 0 until total) {
        val t = i * 0.02
        val x = 4 * kotlin.math.cos(t)
        val y = 0.5 * kotlin.math.sin(t)
        val z = -t * 50
        points.add(Coord3d(x, y, z))
    }

    val lineStrip = LineStrip()
    lineStrip.add(points)
    lineStrip.color = JzyColor.GREEN
    lineStrip.width = 2f

    val quality = Quality.Advanced().setAnimated(true)
    val factory: IChartFactory = EmulGLChartFactory()
    val chart = factory.newChart(quality)
    chart.scene.add(lineStrip)

    chart.view.camera.eye = Coord3d(10.0, 10.0, 10.0)
    chart.view.camera.target = Coord3d(0.0, 0.0, 0.0)

    return chart
}

fun createEquationChart(
    equation: Equation,
    chartType: ChartType,
    mainColor: Color
): Chart? {
    try {
        println("Creating chart with equation: $equation, type: $chartType")
        val quality = Quality.Advanced()
        quality.setAnimated(true)

        val factory: IChartFactory = EmulGLChartFactory()
        val chart = factory.newChart(quality)
        chart.setAnimated(true)
        chart.getView().setSquarifier(XZSquarifier());
        chart.getView().setSquared(true);

        when (chartType) {
            ChartType.SURFACE -> {
                val mapper = object : Mapper() {
                    override fun f(x: Double, y: Double): Double {
                        return ExpressionEvaluator.evaluate(equation.equation, x, y)
                    }
                }

                val xRange = Range(equation.xRange.start.toFloat(), equation.xRange.endInclusive.toFloat())
                val yRange = Range(equation.yRange.start.toFloat(), equation.yRange.endInclusive.toFloat())

                val surface = SurfaceBuilder().orthonormal(
                    OrthonormalGrid(xRange, equation.steps, yRange, equation.steps),
                    mapper
                )

                surface.colorMapper = ColorMapper(
                    ColorMapRainbow(),
                    surface.bounds.zmin.toDouble(),
                    surface.bounds.zmax.toDouble(),
                    JzyColor(200,200,200)
                )

                chart.scene.graph.add(surface)
            }

            ChartType.WIREFRAME -> {
                val xStart = equation.xRange.start
                val xEnd = equation.xRange.endInclusive
                val yStart = equation.yRange.start
                val yEnd = equation.yRange.endInclusive

                val points = mutableListOf<Coord3d>()
                val stepX = (xEnd - xStart) / equation.steps
                val stepY = (yEnd - yStart) / equation.steps

                var x = xStart
                while (x <= xEnd) {
                    var y = yStart
                    while (y <= yEnd) {
                        val z = ExpressionEvaluator.evaluate(equation.equation, x, y)
                        points.add(Coord3d(x, y, z))
                        y += stepY
                    }
                    x += stepX
                }

                val scatter = Scatter(
                    points.toTypedArray(),
                    toJzyColor(mainColor),
                    3f // Size of points in Wireframe mode
                )

                chart.scene.graph.add(scatter)
            }
        }
        val thread:CameraThreadController =  CameraThreadController();
        chart.addController(thread);
        chart.view.isMaximized = true
        chart.view().canvas.dimension.height = 200
        chart.view().canvas.dimension.width = 200
        return chart
    } catch (e: Exception) {
        println("Error creating chart: ${e.message}")
        e.printStackTrace()
        return null
    }
}

private fun toJzyColor(color: Color): JzyColor {
    return JzyColor(color.red, color.green, color.blue, color.alpha)
}