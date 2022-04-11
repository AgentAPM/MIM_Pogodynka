package com.example.weatherapp.network

import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.util.*

data class ClockData(
    var seconds:Int,
    var minutes:Int,
    var hours:Int,
    var month:Int,
    var year:Int
)

