package com.example.weatherapp.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherData(
    val coord:Coord,
    val weather:List<Weather> =listOf(),
    val base:String ="",
    val main:Main,
    val visibility:Int =-1,
    val wind:Wind,
    val clouds:Clouds,
    val dt:Long =0,
    val sys:Sys,
    val timezone:Long =0,
    val id:Long =-1,
    @Json(name = "name")
    val cityName:String? ="Nowhere",
    val cod:Int =-1
):Parcelable{
    companion object {
        val STATUS_OK=200
        val STATUS_APIKEY_ERROR=401
        val STATUS_CITY_NOT_FOUND=404
        val STATUS_CALL_LIMIT_EXCEEDED=429
    }
}
    @Parcelize
    data class Coord(
        val lon:Double =-1.0,
        val lat:Double =-1.0
    ):Parcelable{
        // Double can't be data bound, so the class includes string formatting
        val lonStr:String get() = String.format("%.4f",lon)
        val latStr:String get() = String.format("%.4f",lat)
    }
    @Parcelize
    data class Weather(
        val id:Int =-1,
        val main:String ="",
        val description:String ="",
        val icon:String =""
    ):Parcelable
    @Parcelize
    data class Main(
        val temp:Double =0.0,
        @Json(name = "feels_like")
        val feelsLike:Double =0.0,
        @Json(name = "temp_min")
        val tempMin:Double =0.0,
        @Json(name = "temp_max")
        val tempMax:Double =0.0,
        val pressure:Double =0.0,
        val humidity:Double =0.0
    ):Parcelable{
        // Double can't be data bound, so the class includes string formatting
        val tempStr:String      get() = String.format("%.1f",temp)
        val tempMinStr:String   get() = String.format("%.1f",tempMin)
        val tempMaxStr:String   get() = String.format("%.1f",tempMax)
        val feelsLikeStr:String get() = String.format("%.1f",feelsLike)

        val pressureStr:String  get() = String.format("%.0f",pressure)
        val humidityStr:String  get() = String.format("%.0f",humidity)
    }
    @Parcelize
    data class Wind(
        val speed:Double =0.0,
        val deg:Double =0.0,
        val gust:Double =0.0
    ):Parcelable{
        // Double can't be data bound, so the class includes string formatting
        val speedStr:String get() = String.format("%.2f",speed)
        val degStr:String   get() = String.format("%.2f",deg)
        val gustStr:String  get() = String.format("%.2f",gust)
    }
    @Parcelize
    data class Clouds(
        val all:Int =-1
    ):Parcelable
    @Parcelize
    data class Sys(
        val type:Int =-1,
        val id:Int =-1,
        val country:String ="",
        val sunrise:Long =-1,
        val sunset:Long =-1
    ):Parcelable
/** [find] query omits some fields, so some subclasses need to be adapted */
@Parcelize
data class ClusterWeatherData(
    val message:String ="",
    val cod:Long =-1,
    val count:Int =0,
    val list:List<PlaceData> =listOf()
):Parcelable
@Parcelize
data class PlaceData(
    val coord:Coord,
    val weather:List<Weather> =listOf(),
    val main:Main,
    val wind:PlaceWind,
    val clouds:Clouds,
    val dt:Int =0,
    val sys:PlaceSys,
    val id:Long =-1,
    @Json(name = "name")
    val cityName:String
):Parcelable
@Parcelize
data class PlaceSys(
    val country: String =""
):Parcelable
@Parcelize
data class PlaceWind(
    val speed:Double =0.0,
    val deg:Double =0.0
):Parcelable{
    // Double can't be data bound, so the class includes string formatting
    val speedStr:String get() = String.format("%.2f",speed)
    val degStr:String   get() = String.format("%.2f",deg)
}