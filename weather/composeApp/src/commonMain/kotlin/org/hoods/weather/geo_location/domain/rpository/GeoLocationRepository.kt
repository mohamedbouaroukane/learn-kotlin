package org.hoods.weather.geo_location.domain.rpository

import kotlinx.coroutines.flow.Flow
import org.hoods.weather.geo_location.domain.models.GeoLocation
import org.hoods.weather.utils.ApiErrorResponse
import org.hoods.weather.utils.Response

interface GeoLocationRepository {
    val geoLocation: Flow<GeoLocation?>
    suspend fun upsertLocation(geoLocation: GeoLocation)
    fun fetchGeoLocation(query:String): Flow<Response<List<GeoLocation>, ApiErrorResponse>>
    suspend fun clearGeoLocation()
    suspend fun clear()

}