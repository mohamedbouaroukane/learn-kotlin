package org.hoods.weather.weather.data.mappper

interface ApiMapper<Domain,Model> {
    fun mapToDomain(model: Model,timeZone:String = ""):Domain
}