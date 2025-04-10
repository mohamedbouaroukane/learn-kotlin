package org.hoods.weather.weather.data.mappper

import org.hoods.weather.utils.Util
import org.hoods.weather.utils.WeatherInfoItem
import org.hoods.weather.weather.data.remote.models.HourlyDto
import org.hoods.weather.weather.domain.models.Hourly

class ApiHourlyMapper:ApiMapper<Hourly,HourlyDto> {
    override fun mapToDomain(model: HourlyDto, timeZone: String): Hourly {
        return Hourly(
            temperature = model.temperature2m,
            time = parseTime(time = model.time,timeZone = timeZone),
            weatherStatus = parseWeatherStatus(model.weatherCode)
        )
    }

    private fun parseTime(time: List<Long>, timeZone: String) : List<String>{
    return time.map {
        Util.formatUnixToHour(it, timeZone)
    }
    }
        private fun parseWeatherStatus(code: List<Int>): List<WeatherInfoItem> {
            return code.map {
                Util.getWeatherInfo(it)
            }
        }
}