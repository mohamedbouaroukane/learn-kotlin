package org.hoods.weather.weather.data.mappper

import org.hoods.weather.utils.Util
import org.hoods.weather.utils.WeatherInfoItem
import org.hoods.weather.weather.data.remote.models.DailyDto
import org.hoods.weather.weather.domain.models.Daily

class ApiDailyWeatherMapper : ApiMapper<Daily,DailyDto> {
    override fun mapToDomain(model: DailyDto, timeZone: String): Daily {
        return Daily(
            temperatureMax = model. temperature2mMax,
            temperatureMin = model. temperature2mMin,
            time = parseTime (model. time, timeZone),
            weatherStatus = parseWeatherStatus (model. weatherCode),
            windDirection = parseWeatherDirection(model.windDirection10mDominant),
            sunset = model. sunset.map { Util.formatUnixToHour(it.toLong(), timeZone) },
            sunrise = model. sunrise.map { Util.formatUnixToHour(it, timeZone) },
            uvIndex = model. uvIndexMax,
            windSpeed = model.windSpeed10mMax
        )
    }
    private fun parseTime(time: List<Long>, timeZone: String): List<String> {
        return time.map { Util.formatUnixToDay(it, timeZone) }
    }
    private fun parseWeatherStatus (code: List<Int>): List<WeatherInfoItem> {
        return code.map {
            Util.getWeatherInfo(it)
        }
    }
    private fun parseWeatherDirection (windDirections: List<Double>): List<String> {
        return windDirections.map {
            Util.getWindDirection (it)
        }
    }

}