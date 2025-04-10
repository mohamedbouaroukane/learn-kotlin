package org.hoods.weather.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun <T> LineGraph(
    dataPoints: List<T>,
    xValueMapper: (T) -> String,
    yValueMapper: (T) -> Float,
    modifier: Modifier = Modifier,
    graphTitle: String = "",
    color: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    labelFontSize: TextUnit = 12.sp,
    gradiantColors: List<Color> = listOf(
        MaterialTheme.colorScheme.surfaceContainerHighest,
        Color.Transparent,
    )
) {
    val padding = 16.dp
    val textMeasurer = rememberTextMeasurer()

    Canvas(modifier = modifier.padding(padding)) {
        val maxWidth = size.width
        val maxHeight = size.height

        val xAxisPadding = 60f
        val yAxisPadding = 80f
        val graphPadding = 50f
        val graphWidth = maxWidth - yAxisPadding - graphPadding
        val graphHeight = maxHeight - xAxisPadding - graphPadding

        val yValues = dataPoints.map(yValueMapper)
        val maxYValue = yValues.maxOrNull() ?: 0f
        val minYValue = yValues.minOrNull() ?: 0f
        val yRange = maxYValue - minYValue
        val yUnit = if (yRange > 0f && graphHeight > 0f) graphHeight / yRange else 1f
        val xInterval = graphWidth / (dataPoints.size - 1).coerceAtLeast(1)

        // Clamp Y values
        val points = dataPoints.mapIndexed { index, data ->
            val rawY = maxHeight - graphPadding - (yValueMapper(data) - minYValue) * yUnit
            val clampedY = rawY.coerceIn(graphPadding, maxHeight - xAxisPadding)
            Offset(
                x = yAxisPadding + index * xInterval,
                y = clampedY
            )
        }

        // Draw filled path
        val path = Path().apply {
            points.forEachIndexed { index, point ->
                if (index == 0) moveTo(point.x, maxHeight - xAxisPadding)
                lineTo(point.x, point.y)
            }
            lineTo(points.last().x, maxHeight - xAxisPadding)
            close()
        }

        drawPath(
            path,
            brush = Brush.verticalGradient(
                colors = gradiantColors,
                startY = maxHeight - xAxisPadding,
                endY = graphPadding
            )
        )

        // Draw lines
        points.zipWithNext { start, end ->
            drawLine(
                color = color,
                start = start,
                end = end,
                strokeWidth = 3f
            )
        }

        // X-axis labels
        val labelStep = (dataPoints.size / 6).coerceAtLeast(1)
        dataPoints.forEachIndexed { index, point ->
            if (index % labelStep == 0) {
                val xPosition = yAxisPadding + index * xInterval
                val label = xValueMapper(point)
                val layout = textMeasurer.measure(
                    text = label,
                    style = TextStyle(color = color, fontSize = labelFontSize),
                    constraints = Constraints(maxWidth = size.width.toInt().coerceAtLeast(0))
                )
                val labelOffset = Offset(
                    xPosition - layout.size.width / 2f,
                    maxHeight - yAxisPadding + 30f
                )
                drawText(layout, topLeft = labelOffset)
            }
        }

        // Y-axis values
        val valueStep = yRange / 6f
        for (i in 0..5) {
            val yValue = minYValue + i * valueStep
            val yPosition = maxHeight - xAxisPadding - i * (graphHeight / 5f)
            val layout = textMeasurer.measure(
                text = yValue.roundToInt().toString(),
                style = TextStyle(color = color, fontSize = labelFontSize),
                constraints = Constraints(maxWidth = size.width.toInt().coerceAtLeast(0))
            )
            drawText(
                layout,
                topLeft = Offset(
                    yAxisPadding / 2f - layout.size.width / 2f,
                    yPosition - layout.size.height / 2f
                )
            )
        }

        // Title
        if (graphTitle.isNotEmpty()) {
            val titleStyle = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = (labelFontSize * 1.6)
            )
            val titleLayout = textMeasurer.measure(
                text = graphTitle,
                style = titleStyle,
                constraints = Constraints(maxWidth = size.width.toInt().coerceAtLeast(0))
            )
            drawText(
                textLayoutResult = titleLayout,
                topLeft = Offset(
                    (maxWidth - titleLayout.size.width) / 2f,
                    graphPadding / 2
                )
            )
        }
    }
}
