package org.hoods.weather.ui.weather

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hoods.weather.utils.DEGREE
import org.hoods.weather.weather.domain.models.Hourly
import org.jetbrains.compose.resources.painterResource

@Composable
fun HourlyWeatherDetailedCard(
modifier: Modifier = Modifier,
weatherInfo:Hourly.HourlyInfoItem
) {
    Column (
        modifier = modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text="${weatherInfo.temperature} $DEGREE",
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Icon(
            painter = painterResource(weatherInfo.weatherStatus.icon),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text="${weatherInfo.time} ",
            style = MaterialTheme.typography.bodySmall
        )
    }
}