package org.hoods.weather.ui.weather

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hoods.weather.weather.domain.models.Daily

@Composable
fun UvIndexWeather  (
    modifier: Modifier = Modifier,
    weatherInfo: Daily.WeatherInfo
){
    DetailedInformationCard(
        title = "UV index",
        value = weatherInfo.uvIndex.toString(),
        footer = weatherInfo.weatherStatus.info,
    )
}
