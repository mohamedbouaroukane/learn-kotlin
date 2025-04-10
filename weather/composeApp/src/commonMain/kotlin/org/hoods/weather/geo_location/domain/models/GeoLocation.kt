package org.hoods.weather.geo_location.domain.models

import org.hoods.weather.utils.FlagUrl

data class GeoLocation(
    val id:Int =0,
    val name:String,
    val countryName:String,
    val countryCode:String,
    val flagUrl:FlagUrl,
    val countryId:Int,
    val latitude:Double,
    val longitude:Double,
    val timezone:String,
    val elevation:Double,


)
