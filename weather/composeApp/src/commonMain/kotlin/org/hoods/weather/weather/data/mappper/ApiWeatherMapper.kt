package org.hoods.weather.weather.data.mappper

import org.hoods.weather.weather.data.remote.models.WeatherDto
import org.hoods.weather.weather.domain.models.Weather

class ApiWeatherMapper(
    private val apiDailMapper: ApiDailyWeatherMapper,
    private val currentWeatherMapper: ApiCurrentWeatherMapper,
    private val apiHourlyMapper: ApiHourlyMapper
):ApiMapper<Weather,WeatherDto> {
    override fun mapToDomain(model: WeatherDto, timeZone: String): Weather {
        return Weather(
            currentWeather = currentWeatherMapper.mapToDomain(model.current,timeZone),
             daily = apiDailMapper.mapToDomain(model.daily,timeZone),
            hourly = apiHourlyMapper.mapToDomain(model.hourly,timeZone),
            timezone = timeZone
        )
    }
}