package org.hoods.weather

import android.app.Application
import org.hoods.weather.di.initKoin
import org.koin.android.ext.koin.androidContext


class WeatherApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin{
            androidContext(this@WeatherApp)
        }
    }
}