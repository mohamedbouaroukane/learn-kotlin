package org.hoods.weather.geo_location.data.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import org.hoods.weather.common.data.Mapper
import org.hoods.weather.geo_location.data.local.GeoLocationDao
import org.hoods.weather.geo_location.data.local.models.GeoLocationEntity
import org.hoods.weather.geo_location.data.mapper.GeoLocationMapper
import org.hoods.weather.geo_location.data.remote.GeoLocationRemoteApiService
import org.hoods.weather.geo_location.data.remote.model.toDomain
import org.hoods.weather.geo_location.domain.models.GeoLocation
import org.hoods.weather.geo_location.domain.rpository.GeoLocationRepository
import org.hoods.weather.utils.ApiErrorResponse
import org.hoods.weather.utils.Response
import org.hoods.weather.utils.map

class GeoLocationRepositoryImpl(
    private val geoLocationRemoteApiService: GeoLocationRemoteApiService,
    private val geoLocationDao: GeoLocationDao,
    private val geoLocationMapper: Mapper<GeoLocation,GeoLocationEntity>,
    private val externalScope:CoroutineScope
) : GeoLocationRepository {
    override val geoLocation: Flow<GeoLocation?>
        get() {

            return geoLocationDao.getGeoLocation().map {
                geoLocationMapper.mapToDomainOrNull(it)
                    }.shareIn(scope = externalScope, started = SharingStarted.Lazily)
        }

    override suspend fun upsertLocation(geoLocation: GeoLocation) {
        geoLocationDao.upsertGeoLocation(geoLocationMapper.mapFromDomain(geoLocation))
    }

    override fun fetchGeoLocation(query: String): Flow<Response<List<GeoLocation>, ApiErrorResponse>> {
            return geoLocationRemoteApiService.searchLocation(query).map {
                response ->
                response.map {
                  geoLocationDto ->
                    geoLocationDto.toDomain()
                }
            }
        }

    override suspend fun clearGeoLocation() {
        geoLocationDao.clearGeoLocation()
    }

    override suspend fun clear() {
        externalScope.cancel()
    }
}