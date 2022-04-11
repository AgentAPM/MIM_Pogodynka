package com.example.weatherapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class MODE{SENIOR,JUNIOR}
class MainViewModel:ViewModel() {
    private val _mode = MutableLiveData(MODE.SENIOR)
    val mode: LiveData<MODE> get() = _mode

    fun onClickFAB(){
        Log.d("spr","FAB clicked")
        _mode.value = when(_mode.value){
            MODE.SENIOR -> MODE.JUNIOR
            else -> MODE.SENIOR
        }
    }
}