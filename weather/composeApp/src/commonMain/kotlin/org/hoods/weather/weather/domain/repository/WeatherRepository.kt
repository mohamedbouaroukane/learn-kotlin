package org.hoods.weather.weather.domain.repository

import kotlinx.datetime.TimeZone
import org.hoods.weather.utils.ApiErrorResponse
import org.hoods.weather.utils.Response
import org.hoods.weather.weather.domain.models.Weather
import kotlinx.coroutines.flow.StateFlow

interface WeatherRepository {
    val weatherData: StateFlow<Response<Weather, ApiErrorResponse>?>

    fun fetchWeatherData(
        latitude : Float,
        longitude : Float,
        timeZone: String
    )
}