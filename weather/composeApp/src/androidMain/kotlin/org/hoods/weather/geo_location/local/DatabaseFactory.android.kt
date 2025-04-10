package org.hoods.weather.geo_location.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import org.hoods.weather.geo_location.data.local.GeoLocationDatabase

actual class DatabaseFactory(
    private val context: Context,
) {
    actual fun create(): RoomDatabase.Builder<GeoLocationDatabase> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath (GeoLocationDatabase.DB_NAME)
        return Room.databaseBuilder(
            context = appContext,
            name = dbFile.absolutePath
        )
    }
}