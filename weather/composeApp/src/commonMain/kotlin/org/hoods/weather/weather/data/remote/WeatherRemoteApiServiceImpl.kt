package org.hoods.weather.weather.data.remote

import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import org.hoods.weather.common.data.safeRequest
import org.hoods.weather.utils.ApiErrorResponse
import org.hoods.weather.utils.ApiParameters
import org.hoods.weather.utils.K
import org.hoods.weather.utils.Response
import org.hoods.weather.weather.data.remote.models.WeatherDto

class WeatherRemoteApiServiceImpl(
    private val httpClient: HttpClient
) : WeatherRemoteApiService {
    override fun fetchWeather(
        latitude: Float,
        longitude: Float,
        daily: Array<String>,
        currentWeather: Array<String>,
        hourlyWeather: Array<String>,
        timeformat: String,
        timeZone: String
    ): Flow<Response<WeatherDto, ApiErrorResponse>> {
        return httpClient.safeRequest{
            url(urlString = "${K.FORECAST_BASE_URL}/${K. FORECAST_END_POINT}")
            parameter (ApiParameters.LATITUDE, latitude.toString())
            parameter (ApiParameters.LONGITUDE, longitude.toString())
            parameter(ApiParameters.DAILY, daily.joinToString ( ","))
            parameter (ApiParameters. CURRENT_WEATHER, currentWeather. joinToString (  ","))
            parameter(ApiParameters. HOURLY, hourlyWeather. joinToString( ","))
            parameter (ApiParameters. TIME_FORMAT, timeformat)
            parameter (ApiParameters. TIMEZONE, timeZone)
        }
    }

}