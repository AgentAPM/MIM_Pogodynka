package com.example.weatherapp.citylist

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.*
import com.example.weatherapp.network.*
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*

class ListViewModel(
    val clusterWeatherData: ClusterWeatherData,
    val firstPlaceData: WeatherData
    ): ViewModel() {
    class Factory(
        private val clusterWeatherData: ClusterWeatherData,
        private val firstPlaceData: WeatherData
    ) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
                return ListViewModel(clusterWeatherData,firstPlaceData) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    private val _navigateToSearchPlace = MutableLiveData<WeatherData?>()
    val navigateToSearchPlace:LiveData<WeatherData?> get()=_navigateToSearchPlace
    @SuppressLint("NullSafeMutableLiveData")
    fun resetNavigateToSearchPlace(){_navigateToSearchPlace.value=null}

    private val _placeList= MutableLiveData(clusterWeatherData.list)
    val placeList: LiveData<List<PlaceData>> get()=_placeList
    //private val placeCache = MutableList(clusterWeatherData.list.size){ idx->when(idx){0->firstPlaceData else->null} }
    private val placeCache = mutableMapOf<Long,WeatherData>(Pair(firstPlaceData.id,firstPlaceData))


    //val placeCache:List<WeatherData?> get() = _placeCache

    fun onClickItem(place:PlaceData){
        Log.d("spr","Place clicked. CacheData: ${placeCache[place.id]}")
        if(placeCache[place.id]!=null){
            _navigateToSearchPlace.value = placeCache[place.id]
        } else {
            Log.d("spr","Querying for ${place.cityName}")
            viewModelScope.launch {
                lateinit var newEntry:WeatherData
                try {
                    newEntry = WeatherApi.retrofitService.getByCoords(place.coord.lat,place.coord.lon)

                    Log.d("spr","Got $newEntry")
                }catch (e:Exception){
                    Log.d("spr","Feching data for cache failed. Message: ${e.message}")
                }
                if(newEntry!=null) {
                    placeCache[place.id] = newEntry
                    _navigateToSearchPlace.value = newEntry
                    Log.d("spr", "Entry put. CacheData: ${placeCache[place.id]}")
                }
            }
        }
    }
}

class PlaceDataListener(val clickListener: (place:PlaceData) -> Unit) {
    fun onClick(place: PlaceData) = clickListener(place)
}