package org.hoods.weather.weather.data.remote

import kotlinx.coroutines.flow.Flow
import org.hoods.weather.utils.ApiErrorResponse
import org.hoods.weather.utils.Response
import org.hoods.weather.weather.data.remote.models.WeatherDto

interface WeatherRemoteApiService {

    fun fetchWeather(
        latitude: Float=-6.5f,
        longitude: Float = 39.5f,
        daily: Array<String> = arrayOf(
            "weather_code",
            "temperature_2m_max",
            "temperature_2m_min",
            "wind_speed_10m_max",
            "wind_direction_10m_dominant",
            "sunrise",
            "sunset",
            "uv_index_max",
        ),
            currentWeather: Array<String> = arrayOf(
            "temperature_2m",
            "is_day",
            "weather_code",
            "wind_speed_10m",
            "wind_direction_10m",
        ),
        hourlyWeather: Array<String> = arrayOf(
            "weather_code",
            "temperature_2m",
        ),
        timeFormat: String = "unixtime",
        timeZone: String = "Africa/Dar_es_Salaam"

    ): Flow<Response<WeatherDto, ApiErrorResponse>>
}