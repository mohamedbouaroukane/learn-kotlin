package org.hoods.weather.geo_location.local

import androidx.room.RoomDatabase
import org.hoods.weather.geo_location.data.local.GeoLocationDatabase

actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<GeoLocationDatabase> {
        TODO("Not yet implemented")
    }
}