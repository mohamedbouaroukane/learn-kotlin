package org.hoods.weather.ui.weather

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.unit.dp
import org.hoods.weather.utils.DEGREE
import org.hoods.weather.weather.domain.models.CurrentWeather
import org.jetbrains.compose.resources.painterResource

@Composable
fun CurrentWeather(
    modifier: Modifier = Modifier,
    currentWeather: CurrentWeather,
){
Column(modifier, horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {
    Icon(
        painter = painterResource(resource = currentWeather.weatherStatus.icon),
        contentDescription = null,
        modifier = Modifier.size(120.dp),
        tint = MaterialTheme.colorScheme.onSurfaceVariant
    )
    Spacer(modifier=Modifier.height(8.dp))
    Text(
        text = currentWeather.temperature.toString() + DEGREE,
        style = MaterialTheme.typography.displayMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
    Spacer(modifier=Modifier.height(4.dp))
    Text(
        text = "Weather Status: ${currentWeather.weatherStatus.info}",
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
    Spacer(modifier=Modifier.height(4.dp))

    Text(
        text = "Wind speed: ${currentWeather.windSpeed} km/h  ${currentWeather.windDirection}",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}
}