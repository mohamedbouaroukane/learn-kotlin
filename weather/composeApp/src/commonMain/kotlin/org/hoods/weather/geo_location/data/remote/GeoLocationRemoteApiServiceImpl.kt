package org.hoods.weather.geo_location.data.remote

import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import org.hoods.weather.common.data.safeRequest
import org.hoods.weather.geo_location.data.remote.model.GeoLocationDto
import org.hoods.weather.utils.ApiErrorResponse
import org.hoods.weather.utils.K
import org.hoods.weather.utils.Response

class GeoLocationRemoteApiServiceImpl(
    private val httpClient: HttpClient
) : GeoLocationRemoteApiService {
    override fun searchLocation(query: String): Flow<Response<GeoLocationDto, ApiErrorResponse>> {
        return httpClient.safeRequest<GeoLocationDto,ApiErrorResponse>{
            url(urlString = K.GEO_CODING_BASE_URL + "/${K.GEO_CODING_END_POINT}")
            parameter("name", query)
        }
    }
}