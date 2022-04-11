package com.example.weatherapp.citydisplay

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.network.WeatherData

class PlaceViewModel(val weatherData: WeatherData): ViewModel() {
    class Factory(
        private val weatherData: WeatherData
    ) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PlaceViewModel::class.java)) {
                return PlaceViewModel(weatherData) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    val sunriseTime:Long get()= weatherData.sys.sunrise + weatherData.timezone
    val sunsetTime:Long get()= weatherData.sys.sunset + weatherData.timezone

}