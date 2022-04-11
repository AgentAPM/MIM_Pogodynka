package com.example.weatherapp

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("dn_background")
fun View.setBackgroundToWeatherData(isDay:Boolean){
    setBackgroundResource(
        if(isDay) R.color.main_color
             else R.color.night_color
    )
}

@BindingAdapter("dn_backgroundTint")
fun View.setBackgroundTintToWeatherData(isDay:Boolean){

}

@BindingAdapter("dn_textColor")
fun TextView.setTextColorToWeatherData(isDay:Boolean){
    setTextColor(ContextCompat.getColor(context,
            if(isDay) R.color.main_color
            else R.color.night_color
    ))
}

@BindingAdapter("timeHHMM")
fun TextView.displayTimeHHMM(time:Long){
    val date = Date(time*1000)
    val formatter = SimpleDateFormat("HH:MM")
    Log.d("spr","$date")
    text = formatter.format(date)
}