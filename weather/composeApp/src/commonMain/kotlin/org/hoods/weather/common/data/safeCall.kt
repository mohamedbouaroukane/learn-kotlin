package org.hoods.weather.common.data

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException
import org.hoods.weather.utils.Response


inline fun <reified T,reified E> HttpClient.safeRequest(
    crossinline block:HttpRequestBuilder.()->Unit,
): Flow<Response<T, E>> = flow {
    emit(Response.Loading)
    val response = request {block()}
    emit(Response.Success(response.body<T>()))
}.catch {
    e ->
    e.printStackTrace()
}

suspend inline fun <reified E> ResponseException.errorBody(): E? =
    try{
        response.body()
    }catch(e:SerializationException){
        null
    }