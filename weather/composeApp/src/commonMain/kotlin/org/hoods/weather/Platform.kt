package org.hoods.weather

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform