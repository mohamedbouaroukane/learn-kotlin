package org.hoods.weather.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.hoods.weather.geo_location.domain.models.GeoLocation
import org.hoods.weather.geo_location.domain.rpository.GeoLocationRepository
import org.hoods.weather.utils.Response

class HomeViewModel(
    private val geoLocationRepository: GeoLocationRepository
) :ViewModel() {
    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()

    init {
        getGeoLocation()
    }

    fun saveFavouriteLocation() = viewModelScope.launch {
        _homeState.value.selectedLocation?.let {
            geoLocationRepository.upsertLocation(it)
        }
    }
    fun setSelectedLocation(geoLocation: GeoLocation) {
        _homeState.update {
            it.copy(selectedLocation = geoLocation.copy(id = 1), isLocationSelected = true)
        }
    }
    fun fetchGeoLocation(query: String){
        viewModelScope.launch {
            geoLocationRepository.fetchGeoLocation(query).collect{
                result->
                when(result){
                    is Response.Success ->{
                        _homeState.update {
                            it.copy(isLoading = false,error = null, geoLocations = result.data)
                        }
                    }

                    Response.Error.DefaultError -> {
                        _homeState.update {
                            it.copy(isLoading = false,error = "Error Occurred")
                        }
                    }
                    is Response.Error.HttpError -> {
                        _homeState.update {
                            it.copy(isLoading = false,error = result.code.toString())
                        }
                    }
                    Response.Error.NetworkError -> {
                        _homeState.update {
                            it.copy(isLoading = false,error = "No Network Connection")
                        }
                    }
                    Response.Error.SerializationError -> {
                        _homeState.update {
                            it.copy(isLoading = false,error = "Failed to serialize Data")
                        }
                    }
                    Response.Loading -> {
                        _homeState.update {
                            it.copy(isLoading = true,error = null)
                        }
                    }
                }
            }
        }
    }



    fun getGeoLocation() = viewModelScope.launch{
        geoLocationRepository.geoLocation
            .collect{
                _homeState.update {
                    state ->
                    state.copy(
                        selectedLocation = it,
                        isLocationSelected = it != null
                    )
                }
            }
    }
}


data class HomeState(
    val isLocationSelected: Boolean = false,
    val selectedLocation: GeoLocation? = null,
    val query:String ="",
    val isLoading: Boolean = false,
    val error :String? = null,
    val geoLocations : List<GeoLocation> = emptyList(),
)