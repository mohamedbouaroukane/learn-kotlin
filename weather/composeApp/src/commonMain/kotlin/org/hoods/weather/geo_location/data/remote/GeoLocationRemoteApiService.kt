package org.hoods.weather.geo_location.data.remote

import kotlinx.coroutines.flow.Flow
import org.hoods.weather.geo_location.data.remote.model.GeoLocationDto
import org.hoods.weather.utils.ApiErrorResponse
import org.hoods.weather.utils.Response

interface GeoLocationRemoteApiService {

    fun searchLocation(query:String): Flow<Response<GeoLocationDto,ApiErrorResponse>>
}