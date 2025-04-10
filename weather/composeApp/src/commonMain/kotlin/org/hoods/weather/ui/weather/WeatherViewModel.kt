package org.hoods.weather.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.hoods.weather.geo_location.domain.models.GeoLocation
import org.hoods.weather.geo_location.domain.rpository.GeoLocationRepository
import org.hoods.weather.weather.domain.models.Daily
import org.hoods.weather.weather.domain.models.Weather
import org.hoods.weather.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.update
import org.hoods.weather.utils.Response
import org.hoods.weather.utils.Util

class WeatherViewModel(
    private val repository: WeatherRepository,
    private val geoLocationRepository: GeoLocationRepository
) :ViewModel(){
    private val _weather = MutableStateFlow(WeatherState())
    val weatherState = _weather.asStateFlow()

    init {
        getGeoLocation()
    }
    init {
        observeWeather()
    }

    fun fetchWeatherData() = viewModelScope.launch {
        weatherState.value.selectedLocation?.let {
            geoLocation->
            repository.fetchWeatherData(
                latitude = geoLocation.latitude.toFloat(),
                longitude = geoLocation.longitude.toFloat(),
                timeZone = geoLocation.timezone
            )
        }
    }

    private fun observeWeather()= viewModelScope.launch {
        repository.weatherData.collect{
            response ->
            when(response){
                is Response.Loading ->{
                    _weather.update {
                        it.copy(isLoading = true,error=null)
                    }
                }
                is Response.Success ->{
                    _weather.update {
                        it.copy(isLoading = false,error=null, weather = response.data)
                    }
                    val todayDailyWeatherInfo = response.data.daily.weatherInfo.find {
                        Util.isTodayDate(it.time)
                    }
                    _weather.update {
                        it.copy(dailyWeatherInfo =todayDailyWeatherInfo )
                    }
                }
                Response.Error.DefaultError -> {
                    _weather.update {
                        it.copy(isLoading = false,error = "Error Occurred")
                    }
                }
                is Response.Error.HttpError -> {
                    _weather.update {
                        it.copy(isLoading = false,error = response.code.toString())
                    }
                }
                Response.Error.NetworkError -> {
                    _weather.update {
                        it.copy(isLoading = false,error = "No Network Connection")
                    }
                }
                Response.Error.SerializationError -> {
                    _weather.update {
                        it.copy(isLoading = false,error = "Failed to serialize Data")
                    }
                }
                else ->{}
            }
        }
    }

    fun getGeoLocation() = viewModelScope.launch {
        geoLocationRepository.geoLocation.collect{
            _weather.update{state ->
                state.copy(selectedLocation = it )
            }
        }
    }
}

data class WeatherState(
    val weather:Weather? = null,
    val error: String? = null,
    val isLoading: Boolean = false,
    val dailyWeatherInfo: Daily.WeatherInfo? = null,
    val selectedLocation: GeoLocation? = null
)