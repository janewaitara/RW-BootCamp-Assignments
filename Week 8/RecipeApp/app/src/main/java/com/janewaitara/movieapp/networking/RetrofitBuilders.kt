package com.janewaitara.movieapp.networking

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

fun buildClient(): OkHttpClient =
    OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.MINUTES) // connect timeout
        .writeTimeout(5, TimeUnit.MINUTES) // write timeout
        .readTimeout(5, TimeUnit.MINUTES) // read timeout
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY //want to log the body level of the request which mean little report of sent and received data
        })
        .build()

fun buildMoshi(): Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//retrofit client factory fun
fun buildRetrofit(): Retrofit {
    //val contentType = "application/json".toMediaType()
    return Retrofit.Builder()
        .client(buildClient())
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(buildMoshi()).asLenient()) // automatically parses the json and gives it the type that you need and asLenient gives a more forgiving parser
        .build()
}

//create an API service from the class you have defined
fun buildApiService(): RemoteApiService =
    buildRetrofit().create(RemoteApiService::class.java)