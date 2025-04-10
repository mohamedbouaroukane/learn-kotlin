package org.hoods.weather.geo_location.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import org.hoods.weather.geo_location.data.local.models.GeoLocationEntity

@Database(entities = [GeoLocationEntity::class], version = 1)
@ConstructedBy(GeoLocationConstructor::class)
abstract class GeoLocationDatabase:RoomDatabase() {
    companion object{
        const val DB_NAME = "geo_location.db"
    }
    abstract fun geoLocationDao(): GeoLocationDao
}