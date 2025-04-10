package org.hoods.weather.ui.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hoods.weather.weather.domain.models.Daily
import org.hoods.weather.weather.domain.models.Weather

@Composable
fun SunSetWeather (
    modifier: Modifier = Modifier,
    weatherInfo:Daily.WeatherInfo
    ){
    DetailedInformationCard(
        title = "Sunrise",
        value = weatherInfo.sunrise,
        footer = "Sunset: ${weatherInfo.sunset}",
    )
}
