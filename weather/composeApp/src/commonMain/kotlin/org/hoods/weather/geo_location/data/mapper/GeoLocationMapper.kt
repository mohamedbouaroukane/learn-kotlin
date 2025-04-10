package org.hoods.weather.geo_location.data.mapper

import org.hoods.weather.common.data.Mapper
import org.hoods.weather.geo_location.data.local.models.GeoLocationEntity
import org.hoods.weather.geo_location.domain.models.GeoLocation
import org.hoods.weather.utils.K

class GeoLocationMapper: Mapper<GeoLocation, GeoLocationEntity> {
    override fun mapToDomainOrNull(model: GeoLocationEntity?): GeoLocation? {
        return model?.run {
            GeoLocation(
                id=id,
                name = name,
                latitude = latitude,
                longitude = longitude,
                timezone = timezone,
                countryName = countryName,
                countryCode = countryCode,
                countryId = countryId,
                flagUrl = K.flagUrl(countryCode),
                elevation = elevation
            )
        }
    }

    override fun mapFromDomain(domain: GeoLocation): GeoLocationEntity {
        return domain.run {
            GeoLocationEntity(
                id=id,
                name=name,
                latitude = latitude,
                longitude = longitude,
                timezone = timezone,
                countryName = countryName,
                countryCode = countryCode,
                countryId = countryId,
                elevation = elevation

                )
        }
    }
}