package org.hoods.weather.weather.data.mappper

import org.hoods.weather.utils.Util
import org.hoods.weather.utils.WeatherInfoItem
import org.hoods.weather.weather.data.remote.models.CurrentDto
import org.hoods.weather.weather.domain.models.CurrentWeather

class ApiCurrentWeatherMapper :ApiMapper<CurrentWeather,CurrentDto>{
    override fun mapToDomain(model: CurrentDto, timeZone: String): CurrentWeather {
        return CurrentWeather(
            temperature = model.temperature2m,
            time = parseTime(model.time,timeZone),
            weatherStatus = parseWeatherStatus(model.weatherCode),
            windDirection = parseWindDirection(model.windDirection10m),
            windSpeed = model.windSpeed10m,
            isDay = model.isDay == 1
        )
    }
    private fun parseTime(time: Long, timeZone: String) : String {
        return Util.formatUnixToCustom(time, timeZone = timeZone)
    }
        private fun parseWeatherStatus(code: Int): WeatherInfoItem {
            return Util.getWeatherInfo(code)
        }
        private fun parseWindDirection(windDirection: Double): String {
            return Util.getWindDirection(windDirection)
        }
}