package org.hoods.weather.geo_location.data.local

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object GeoLocationConstructor:RoomDatabaseConstructor<GeoLocationDatabase> {

    override fun initialize(): GeoLocationDatabase

}