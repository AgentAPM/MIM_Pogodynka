package com.example.weatherapp.search

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.Util
import com.example.weatherapp.network.ClusterWeatherData
import com.example.weatherapp.network.WeatherApi
import com.example.weatherapp.network.WeatherApiService
import com.example.weatherapp.network.WeatherData
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.Integer.min
import java.lang.Integer.max

class SearchViewModel : ViewModel() {
    val cityName = MutableLiveData<String>()
    val clusterRange=MutableLiveData(10)

    val step_boundary=10
    val big_step=5

    var isProcessing=false

    fun changeClusterRange(n:Int){
        val old = clusterRange.value?:10
        clusterRange.value = max(WeatherApiService.minRange, min(WeatherApiService.maxRange, old+n))
    }
    fun stepClusterRange(n:Int){
        val old = clusterRange.value?:10
        if((n==1 && old>=step_boundary) || (n==-1 && old>step_boundary))
            clusterRange.value = max(WeatherApiService.minRange, min(WeatherApiService.maxRange, old+n*big_step))
        else
            clusterRange.value = max(WeatherApiService.minRange, min(WeatherApiService.maxRange, old+n))
    }

    private val _navigateToSearchPlace = MutableLiveData<WeatherData?>()
    val navigateToSearchPlace:LiveData<WeatherData?> get()=_navigateToSearchPlace
    @SuppressLint("NullSafeMutableLiveData")
    fun resetNavigateToSearchPlace(){
        Log.d("spr","onResetPlace: ${navigateToSearchPlace.value}")
        _navigateToSearchPlace.value=null
        Log.d("spr","afterResetPlace: ${navigateToSearchPlace.value}")
    }

    private val _navigateToSearchCluster = MutableLiveData<ClusterWeatherData?>()
    val navigateToSearchCluster:LiveData<ClusterWeatherData?> get()=_navigateToSearchCluster
    @SuppressLint("NullSafeMutableLiveData")
    fun resetNavigateToSearchCluster(){_navigateToSearchCluster.value=null}
    var searchClusterFirstPlaceData:WeatherData? = null

    private val _showError = MutableLiveData<String>()
    val showError:LiveData<String> get() = _showError
    fun resetShowError() {_showError.value=""}

    fun onClickSearchPlace(){
        Log.d("spr","OnClickSearchPlace v=${navigateToSearchPlace.value}")
        if(cityName.value.isNullOrEmpty()) return
        viewModelScope.launch {
            try {
                val result = WeatherApi.retrofitService.getByName(cityName.value!!)
                //Log.d("spr","Time: ${Util.LongToDate(result.dt)}")
                _navigateToSearchPlace.value = result
            }catch(e:Exception){
                Log.d("spr", "Message: ${e.message}")
                _showError.value = "Nie znaleziono miasta"
            }
            Log.d("spr","AfterClickSearchPlace v=${navigateToSearchPlace.value}")
        }
    }
    fun onClickSearchCluster(){
        if(cityName.value.isNullOrEmpty()) return
        viewModelScope.launch {
            try {
                val result = WeatherApi.retrofitService.getByName(cityName.value!!)
                val cluster = WeatherApi.retrofitService.getNearbyByCoords(
                    result.coord.lat, result.coord.lon, clusterRange.value!!)
                searchClusterFirstPlaceData = result
                _navigateToSearchCluster.value = cluster

            }catch(e:Exception){
                Log.d("spr", "Message: ${e.message}")
                _showError.value = "Nie znaleziono miasta"
            }
        }
    }
}