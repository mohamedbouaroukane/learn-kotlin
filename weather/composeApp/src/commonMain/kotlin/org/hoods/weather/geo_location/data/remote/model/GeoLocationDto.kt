package org.hoods.weather.geo_location.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.hoods.weather.geo_location.domain.models.GeoLocation
import org.hoods.weather.utils.K

@Serializable
data class GeoLocationDto(
    @SerialName("generationtime_ms")
    val generationtimeMs: Double = 0.0,
    @SerialName("results")
    val results: List<Result> = listOf()
)


fun GeoLocationDto.toDomain(): List<GeoLocation> {
    return results.map {
        GeoLocation(
            id = it.id,
            name = it.name,
            countryName = it.country,
            countryId = it.countryId,
            latitude = it.latitude,
            longitude = it.longitude,
            flagUrl = K.flagUrl(it.countryCode),
            countryCode = it.countryCode,
            timezone = it.timezone,
            elevation = it.elevation,

        )
    }
}