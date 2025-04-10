package org.hoods.weather.weather.domain.models

data class Weather (
    val currentWeather: CurrentWeather,
    val daily: Daily,
    val hourly: Hourly,
    val timezone: String,
)