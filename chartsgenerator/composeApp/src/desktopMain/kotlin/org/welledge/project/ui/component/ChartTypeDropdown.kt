package org.welledge.project.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import org.welledge.project.domain.model.ChartType

@Composable
fun ChartTypeDropdown(
    selected: ChartType,
    onSelected: (ChartType) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(onClick = { expanded = true },
           ) {
            Text("Chart type: ${selected.name}", color = Color.White)
        }

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            ChartType.entries.forEach {
                DropdownMenuItem(

                    onClick = {
                        onSelected(it)
                        expanded = false
                    }
                ){
                    Text(it.name)
                }
            }
        }
    }
}