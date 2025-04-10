package org.hoods.weather.geo_location.`data`.local

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.EntityUpsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Double
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow
import org.hoods.weather.geo_location.`data`.local.models.GeoLocationEntity

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class GeoLocationDao_Impl(
  __db: RoomDatabase,
) : GeoLocationDao {
  private val __db: RoomDatabase

  private val __upsertAdapterOfGeoLocationEntity: EntityUpsertAdapter<GeoLocationEntity>
  init {
    this.__db = __db
    this.__upsertAdapterOfGeoLocationEntity = EntityUpsertAdapter<GeoLocationEntity>(object :
        EntityInsertAdapter<GeoLocationEntity>() {
      protected override fun createQuery(): String =
          "INSERT INTO `geolocation_tabel` (`id`,`name`,`countryName`,`countryCode`,`countryId`,`longitude`,`latitude`,`timezone`,`elevation`) VALUES (?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: GeoLocationEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.countryName)
        statement.bindText(4, entity.countryCode)
        statement.bindLong(5, entity.countryId.toLong())
        statement.bindDouble(6, entity.longitude)
        statement.bindDouble(7, entity.latitude)
        statement.bindText(8, entity.timezone)
        statement.bindDouble(9, entity.elevation)
      }
    }, object : EntityDeleteOrUpdateAdapter<GeoLocationEntity>() {
      protected override fun createQuery(): String =
          "UPDATE `geolocation_tabel` SET `id` = ?,`name` = ?,`countryName` = ?,`countryCode` = ?,`countryId` = ?,`longitude` = ?,`latitude` = ?,`timezone` = ?,`elevation` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: GeoLocationEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.countryName)
        statement.bindText(4, entity.countryCode)
        statement.bindLong(5, entity.countryId.toLong())
        statement.bindDouble(6, entity.longitude)
        statement.bindDouble(7, entity.latitude)
        statement.bindText(8, entity.timezone)
        statement.bindDouble(9, entity.elevation)
        statement.bindLong(10, entity.id.toLong())
      }
    })
  }

  public override suspend fun upsertGeoLocation(geoLocationEntity: GeoLocationEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __upsertAdapterOfGeoLocationEntity.upsert(_connection, geoLocationEntity)
  }

  public override fun getGeoLocation(): Flow<GeoLocationEntity?> {
    val _sql: String = "select * from geolocation_tabel Limit 1"
    return createFlow(__db, false, arrayOf("geolocation_tabel")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfCountryName: Int = getColumnIndexOrThrow(_stmt, "countryName")
        val _columnIndexOfCountryCode: Int = getColumnIndexOrThrow(_stmt, "countryCode")
        val _columnIndexOfCountryId: Int = getColumnIndexOrThrow(_stmt, "countryId")
        val _columnIndexOfLongitude: Int = getColumnIndexOrThrow(_stmt, "longitude")
        val _columnIndexOfLatitude: Int = getColumnIndexOrThrow(_stmt, "latitude")
        val _columnIndexOfTimezone: Int = getColumnIndexOrThrow(_stmt, "timezone")
        val _columnIndexOfElevation: Int = getColumnIndexOrThrow(_stmt, "elevation")
        val _result: GeoLocationEntity?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpCountryName: String
          _tmpCountryName = _stmt.getText(_columnIndexOfCountryName)
          val _tmpCountryCode: String
          _tmpCountryCode = _stmt.getText(_columnIndexOfCountryCode)
          val _tmpCountryId: Int
          _tmpCountryId = _stmt.getLong(_columnIndexOfCountryId).toInt()
          val _tmpLongitude: Double
          _tmpLongitude = _stmt.getDouble(_columnIndexOfLongitude)
          val _tmpLatitude: Double
          _tmpLatitude = _stmt.getDouble(_columnIndexOfLatitude)
          val _tmpTimezone: String
          _tmpTimezone = _stmt.getText(_columnIndexOfTimezone)
          val _tmpElevation: Double
          _tmpElevation = _stmt.getDouble(_columnIndexOfElevation)
          _result =
              GeoLocationEntity(_tmpId,_tmpName,_tmpCountryName,_tmpCountryCode,_tmpCountryId,_tmpLongitude,_tmpLatitude,_tmpTimezone,_tmpElevation)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun clearGeoLocation() {
    val _sql: String = "DELETE FROM geolocation_tabel"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
