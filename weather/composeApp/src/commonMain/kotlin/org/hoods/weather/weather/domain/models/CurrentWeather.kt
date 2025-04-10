package org.hoods.weather.weather.domain.models

import org.hoods.weather.utils.WeatherInfoItem

data class CurrentWeather(
    val temperature: Double,
    val time: String,
    val weatherStatus: WeatherInfoItem,
    val windDirection: String,
    val windSpeed: Double,
    val isDay: Boolean,
)