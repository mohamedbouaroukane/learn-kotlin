package org.hoods.weather.geo_location.data.local

import androidx.room.Room
import androidx.room.RoomDatabase
import org.hoods.weather.geo_location.data.local.GeoLocationDatabase
import java.io.File

actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<GeoLocationDatabase> {
        val os = System.getProperty("os.name"). lowercase()
        val userHome = System.getProperty("user.home")
        val appDataDir = when {
            os.contains("win") -> File (System.getenv("APPDATA"), "weather")
            os.contains("mac") -> File(userHome, "Library/Application Support/weather")
            else -> File(userHome, ".local/share/weather")
        }
        if(!appDataDir.exists()) {
            appDataDir.mkdirs()
        }
        val dbFile = File(appDataDir, GeoLocationDatabase.DB_NAME)
        return Room.databaseBuilder(dbFile.absolutePath)
    }
}