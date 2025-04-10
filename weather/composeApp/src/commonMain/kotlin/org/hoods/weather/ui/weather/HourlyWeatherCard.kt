package org.hoods.weather.ui.weather

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Clock
import org.hoods.weather.utils.Util
import org.hoods.weather.weather.domain.models.Hourly

@Composable
fun HourlyWeatherCard(
    modifier: Modifier = Modifier,
    hourly: Hourly
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Today",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = Util.formatUnixToCustom(Clock.System.now().toEpochMilliseconds() / 1000),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            HorizontalDivider(
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            LazyRow(
                modifier = Modifier.padding(16.dp)
            ) {
                items(hourly.weatherInfo){
                    infoItem->
                    HourlyWeatherDetailedCard(weatherInfo = infoItem)
                }
            }
        }

    }

}