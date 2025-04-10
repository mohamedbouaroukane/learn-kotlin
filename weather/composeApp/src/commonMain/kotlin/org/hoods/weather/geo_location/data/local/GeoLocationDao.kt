package org.hoods.weather.geo_location.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import org.hoods.weather.geo_location.data.local.models.GeoLocationEntity

@Dao
interface GeoLocationDao {

    @Upsert
    @Transaction
    suspend fun upsertGeoLocation(geoLocationEntity: GeoLocationEntity)

    @Query("select * from geolocation_tabel Limit 1")
    fun getGeoLocation() : Flow<GeoLocationEntity?>

    @Query("DELETE FROM geolocation_tabel")
    @Transaction
    suspend fun clearGeoLocation()
}