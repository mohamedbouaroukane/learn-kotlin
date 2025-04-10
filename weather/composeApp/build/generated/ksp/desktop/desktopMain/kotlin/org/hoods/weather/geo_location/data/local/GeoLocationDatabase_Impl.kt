package org.hoods.weather.geo_location.`data`.local

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class GeoLocationDatabase_Impl : GeoLocationDatabase() {
  private val _geoLocationDao: Lazy<GeoLocationDao> = lazy {
    GeoLocationDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(1,
        "e7c439f1572762ff55727e5ed48e5faf", "39fbc129ee7d0137fb6b6bce1ee84b1b") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `geolocation_tabel` (`id` INTEGER NOT NULL, `name` TEXT NOT NULL, `countryName` TEXT NOT NULL, `countryCode` TEXT NOT NULL, `countryId` INTEGER NOT NULL, `longitude` REAL NOT NULL, `latitude` REAL NOT NULL, `timezone` TEXT NOT NULL, `elevation` REAL NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'e7c439f1572762ff55727e5ed48e5faf')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `geolocation_tabel`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection):
          RoomOpenDelegate.ValidationResult {
        val _columnsGeolocationTabel: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsGeolocationTabel.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsGeolocationTabel.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsGeolocationTabel.put("countryName", TableInfo.Column("countryName", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsGeolocationTabel.put("countryCode", TableInfo.Column("countryCode", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsGeolocationTabel.put("countryId", TableInfo.Column("countryId", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsGeolocationTabel.put("longitude", TableInfo.Column("longitude", "REAL", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsGeolocationTabel.put("latitude", TableInfo.Column("latitude", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsGeolocationTabel.put("timezone", TableInfo.Column("timezone", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsGeolocationTabel.put("elevation", TableInfo.Column("elevation", "REAL", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysGeolocationTabel: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesGeolocationTabel: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoGeolocationTabel: TableInfo = TableInfo("geolocation_tabel",
            _columnsGeolocationTabel, _foreignKeysGeolocationTabel, _indicesGeolocationTabel)
        val _existingGeolocationTabel: TableInfo = read(connection, "geolocation_tabel")
        if (!_infoGeolocationTabel.equals(_existingGeolocationTabel)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |geolocation_tabel(org.hoods.weather.geo_location.data.local.models.GeoLocationEntity).
              | Expected:
              |""".trimMargin() + _infoGeolocationTabel + """
              |
              | Found:
              |""".trimMargin() + _existingGeolocationTabel)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "geolocation_tabel")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(GeoLocationDao::class, GeoLocationDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override
      fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>):
      List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun geoLocationDao(): GeoLocationDao = _geoLocationDao.value
}
