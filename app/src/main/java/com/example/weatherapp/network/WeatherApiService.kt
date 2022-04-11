package com.example.weatherapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val apiKey = "bad6e097930f7967509f972acfb82c85"
private const val BASE_URL = "https://api.openweathermap.org/data/2.5/" // /weather?q={city name}&appid={API key}"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory( MoshiConverterFactory.create(moshi) )
    .build()

interface WeatherApiService {
    @GET("weather")
    suspend fun getByName(@Query("q") cityName:String, @Query("lang") lang:String = "pl", @Query("units") units:String="metric", @Query("appid") key:String=apiKey):WeatherData
    @GET("weather")
    suspend fun getByCoords(@Query("lat") lat:Double, @Query("lon") lon:Double, @Query("lang") lang:String = "pl", @Query("units") units:String="metric", @Query("appid") key:String=apiKey):WeatherData
    @GET("find")
    suspend fun getNearbyByCoords(@Query("lat") lat:Double, @Query("lon") lon:Double, @Query("cnt") n:Int=10, @Query("lang") lang:String = "pl", @Query("units") units:String="metric", @Query("appid") key:String=apiKey):ClusterWeatherData

    companion object {
        val minRange = 1
        val maxRange = 50
    }
}

object WeatherApi {
    val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}