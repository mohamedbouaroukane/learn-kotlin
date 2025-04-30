package org.welledge.project.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val MyPrimary = Color(0xFF1E88E5)      // أزرق
val MySecondary = Color(0xFFD81B60)    // وردي
val MyBackground = Color(0xFFF3F3F3)   // رمادي فاتح
val MySurface = Color(0xFFFFFFFF)      // أبيض
val MyOnPrimary = Color(0xFFFFFFFF)    // أبيض للنص
val MyOnSecondary = Color(0xFFFFFFFF)
val MyOnBackground = Color(0xFF000000)
val MyOnSurface = Color(0xFF000000)

private val MyColorScheme = lightColorScheme(
    primary = MyPrimary,
    onPrimary = MyOnPrimary,
    secondary = MySecondary,
    onSecondary = MyOnSecondary,
    background = MyBackground,
    onBackground = MyOnBackground,
    surface = MySurface,
    onSurface = MyOnSurface,
)

@Composable
fun MyAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MyColorScheme,
        typography = Typography(),
        content = content
    )
}