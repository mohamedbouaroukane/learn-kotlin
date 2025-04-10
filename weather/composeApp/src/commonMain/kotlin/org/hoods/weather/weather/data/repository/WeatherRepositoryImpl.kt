package org.hoods.weather.weather.data.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.datetime.TimeZone
import org.hoods.weather.utils.ApiErrorResponse
import org.hoods.weather.utils.Response
import org.hoods.weather.weather.data.mappper.ApiWeatherMapper
import org.hoods.weather.weather.data.remote.WeatherRemoteApiService
import org.hoods.weather.weather.domain.models.Weather
import org.hoods.weather.weather.domain.repository.WeatherRepository
import org.hoods.weather.utils.map

class WeatherRepositoryImpl(
    private val weatherRemoteApiService: WeatherRemoteApiService,
    private val mapper:ApiWeatherMapper,
    private val externalScope: CoroutineScope
) : WeatherRepository {
    private val _weatherData = MutableStateFlow<Response<Weather,ApiErrorResponse>?>(null)
    override val weatherData: StateFlow<Response<Weather, ApiErrorResponse>?>
        get() = _weatherData.asStateFlow()

    override fun fetchWeatherData(latitude: Float, longitude: Float, timeZone: String) {
        weatherRemoteApiService.fetchWeather(
            longitude = longitude,
            latitude = latitude,
            timeZone = timeZone
        ).map {
            response -> response.map {
                mapper.mapToDomain(it,timeZone = timeZone)
        }
        }.onEach {
            result ->
            _weatherData.update{
                result
        }

        }.launchIn(externalScope)
    }
}