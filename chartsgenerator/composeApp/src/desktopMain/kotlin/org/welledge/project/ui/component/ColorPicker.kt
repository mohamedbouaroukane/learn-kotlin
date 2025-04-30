package org.welledge.project.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColorPicker(
    selectedColor: Color,
    onColorSelected: (Color) -> Unit
) {
    val colors = listOf(
        Color.Red,
        Color.Blue,
        Color(0xFF9C27B0), // Purple
        Color.Yellow,
        Color(0xFFFF9800)  // Orange
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(vertical = 16.dp)
    ) {
        colors.forEach { color ->
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = color,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { onColorSelected(color) }
                    .border(
                        width = if (color == selectedColor) 3.dp else 1.dp,
                        color = if (color == selectedColor) Color.Gray else color,
                        shape = RoundedCornerShape(8.dp)
                    )
            )
        }
    }
}