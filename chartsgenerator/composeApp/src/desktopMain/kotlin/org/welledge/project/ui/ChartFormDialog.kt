package org.welledge.project.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import org.welledge.project.presentation.viewmodel.ChartViewModel
import org.welledge.project.ui.component.ChartTypeDropdown
import org.welledge.project.ui.component.ColorPicker

@Composable
fun ChartFormDialog(
    viewModel: ChartViewModel,
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit
) {
    val input = viewModel.input
    val chartType = viewModel.chartType

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text("Chart Settings") },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .zIndex(100f)
            ) {
                // Equation
                Text(text = "Equation:")
                TextField(
                    value = input.equation,
                    onValueChange = { viewModel.updateEquation(it) },
                    label = { Text("e.g. sin(x*y)") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // X Range
                Text(text = "X Range:")
                Row {
                    var xStart by remember { mutableStateOf(input.xRange.start.toString()) }
                    var xEnd by remember { mutableStateOf(input.xRange.endInclusive.toString()) }

                    TextField(
                        value = xStart,
                        onValueChange = {
                            xStart = it
                            viewModel.updateXRange(it.toDoubleOrNull() ?: 0.0, xEnd.toDoubleOrNull() ?: 0.0)
                        },
                        label = { Text("From") },
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    TextField(
                        value = xEnd,
                        onValueChange = {
                            xEnd = it
                            viewModel.updateXRange(xStart.toDoubleOrNull() ?: 0.0, it.toDoubleOrNull() ?: 0.0)
                        },
                        label = { Text("To") },
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Y Range
                Text(text = "Y Range:")
                Row {
                    var yStart by remember { mutableStateOf(input.yRange.start.toString()) }
                    var yEnd by remember { mutableStateOf(input.yRange.endInclusive.toString()) }

                    TextField(
                        value = yStart,
                        onValueChange = {
                            yStart = it
                            viewModel.updateYRange(it.toDoubleOrNull() ?: 0.0, yEnd.toDoubleOrNull() ?: 0.0)
                        },
                        label = { Text("From") },
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    TextField(
                        value = yEnd,
                        onValueChange = {
                            yEnd = it
                            viewModel.updateYRange(yStart.toDoubleOrNull() ?: 0.0, it.toDoubleOrNull() ?: 0.0)
                        },
                        label = { Text("To") },
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Chart Type
                Text(text = "Chart Type:")
                ChartTypeDropdown(selected = chartType) {
                    viewModel.updateChartType(it)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Color Picker
                Text(text = "Choose Color:")
                ColorPicker(
                    selectedColor = viewModel.color,
                    onColorSelected = { viewModel.updateColor(it) }
                )
            }
        },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Generate")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Cancel")
            }
        }
    )
}
