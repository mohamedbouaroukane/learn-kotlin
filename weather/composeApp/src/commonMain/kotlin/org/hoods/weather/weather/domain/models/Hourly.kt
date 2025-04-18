package org.hoods.weather.weather.domain.models

import org.hoods.weather.utils.WeatherInfoItem

data class Hourly(
    private val temperature: List<Double>,
    private val time: List<String>,
    private val weatherStatus: List<WeatherInfoItem>
) {
    val weatherInfo: List<HourlyInfoItem>
    get() {
        return time.mapIndexed {index, time ->
            HourlyInfoItem(
                 temperature[index],
                time = time,
                weatherStatus = weatherStatus[index]
            )
        }
    }
    data class HourlyInfoItem(
        val temperature: Double,
        val time: String,
        val weatherStatus: WeatherInfoItem
    )
}