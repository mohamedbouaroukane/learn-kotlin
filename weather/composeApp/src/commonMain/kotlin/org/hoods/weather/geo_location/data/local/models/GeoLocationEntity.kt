package org.hoods.weather.geo_location.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("geolocation_tabel")
data class GeoLocationEntity(
    @PrimaryKey(autoGenerate = false)
    val id:Int = 1,
    val name:String,
    val countryName:String,
    val countryCode:String,
    val countryId:Int,
    val longitude:Double,
    val latitude:Double,
    val timezone:String,
    val elevation:Double
    )
