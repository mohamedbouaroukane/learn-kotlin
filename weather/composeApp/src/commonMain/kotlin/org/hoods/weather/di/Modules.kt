package org.hoods.weather.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.hoods.weather.common.data.HttpClientFactory
import org.hoods.weather.common.data.Mapper
import org.hoods.weather.geo_location.data.local.DatabaseFactory
import org.hoods.weather.geo_location.data.local.GeoLocationDatabase
import org.hoods.weather.geo_location.data.local.models.GeoLocationEntity
import org.hoods.weather.geo_location.data.mapper.GeoLocationMapper
import org.hoods.weather.geo_location.data.remote.GeoLocationRemoteApiService
import org.hoods.weather.geo_location.data.remote.GeoLocationRemoteApiServiceImpl
import org.hoods.weather.geo_location.data.repository.GeoLocationRepositoryImpl
import org.hoods.weather.geo_location.domain.models.GeoLocation
import org.hoods.weather.geo_location.domain.rpository.GeoLocationRepository
import org.hoods.weather.ui.home.HomeViewModel
import org.hoods.weather.utils.provideExternalCoroutineScope
import org.hoods.weather.weather.data.mappper.ApiMapper
import org.hoods.weather.weather.data.remote.WeatherRemoteApiService
import org.hoods.weather.weather.data.remote.models.CurrentDto
import org.hoods.weather.weather.data.remote.models.DailyDto
import org.hoods.weather.weather.data.remote.models.HourlyDto
import org.hoods.weather.weather.data.remote.models.WeatherDto
import org.hoods.weather.weather.domain.models.CurrentWeather
import org.hoods.weather.weather.domain.models.Daily
import org.hoods.weather.weather.domain.models.Hourly
import org.hoods.weather.weather.domain.models.Weather
import org.hoods.weather.weather.domain.repository.WeatherRepository

import org.hoods.weather.weather.data.remote.WeatherRemoteApiServiceImpl
import org.hoods.weather.weather.data.mappper.ApiHourlyMapper
import org.hoods.weather.weather.data.mappper.ApiDailyWeatherMapper
import org.hoods.weather.weather.data.mappper.ApiCurrentWeatherMapper
import org.hoods.weather.weather.data.mappper.ApiWeatherMapper
import org.hoods.weather.ui.weather.WeatherViewModel

import org.hoods.weather.weather.data.repository.WeatherRepositoryImpl

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule:Module

val sharedModel = module{
            single {
                get<DatabaseFactory>().create()
                    .setDriver(BundledSQLiteDriver())
                    .build()
            }
    single {
        get<GeoLocationDatabase>().geoLocationDao()
    }
    single {
        HttpClientFactory.create(get())
    }
    single {
        provideExternalCoroutineScope()
    }.bind()

    singleOf(::GeoLocationRemoteApiServiceImpl).bind<GeoLocationRemoteApiService>()
    singleOf(::GeoLocationRepositoryImpl).bind< GeoLocationRepository>()
    singleOf(::GeoLocationMapper).bind<Mapper<GeoLocation, GeoLocationEntity>>()
    singleOf(::GeoLocationRepositoryImpl).bind <GeoLocationRepository>()
    singleOf(::WeatherRemoteApiServiceImpl) .bind<WeatherRemoteApiService>()
    singleOf(::ApiDailyWeatherMapper) .bind<ApiMapper<Daily, DailyDto>>()
    singleOf(::ApiHourlyMapper) . bind<ApiMapper<Hourly, HourlyDto>>()
    singleOf(::ApiWeatherMapper) .bind<ApiMapper<Weather, WeatherDto>>()
    singleOf(::ApiCurrentWeatherMapper) . bind<ApiMapper<CurrentWeather, CurrentDto>>()
    singleOf(::WeatherRepositoryImpl).bind<WeatherRepository>()


    viewModelOf(::HomeViewModel)
    viewModelOf(::WeatherViewModel)
}