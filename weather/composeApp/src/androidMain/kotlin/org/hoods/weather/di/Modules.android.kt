package org.hoods.weather.di

import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*
import org.hoods.weather.geo_location.data.local.DatabaseFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> {
            OkHttp.create()
        }
        single {
            DatabaseFactory(androidApplication())
        }
    }