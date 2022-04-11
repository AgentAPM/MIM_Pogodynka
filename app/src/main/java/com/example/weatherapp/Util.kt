package com.example.weatherapp

import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.network.ClockData
import java.util.*


object Util {

    lateinit var activity: AppCompatActivity
    lateinit var resources: Resources

    fun setActionBarTitle(text:String) {
        val bar = activity.supportActionBar
        bar?.title = text
    }
    fun setActionBarColor(resID:Int) {
        val bar = activity.supportActionBar
        bar?.setBackgroundDrawable(ColorDrawable(resources.getColor(resID)))
    }
    fun setStatusBarColor(resID:Int){
        activity.window.statusBarColor = resources.getColor(resID)
    }

    fun LongToDate(time:Long): ClockData {
        val date = Date(time*1000)
        return ClockData(
            date.seconds,
            date.minutes,
            date.hours,
            date.month,
            date.year
        )
    }
    fun formatDateHHMM(date:Date){

    }
}