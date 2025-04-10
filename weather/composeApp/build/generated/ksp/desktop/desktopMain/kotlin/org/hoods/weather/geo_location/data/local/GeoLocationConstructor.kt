package org.hoods.weather.geo_location.`data`.local

import androidx.room.RoomDatabaseConstructor

public actual object GeoLocationConstructor : RoomDatabaseConstructor<GeoLocationDatabase> {
  actual override fun initialize(): GeoLocationDatabase =
      org.hoods.weather.geo_location.`data`.local.GeoLocationDatabase_Impl()
}
