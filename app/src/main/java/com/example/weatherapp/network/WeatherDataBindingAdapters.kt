package com.example.weatherapp.network

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.weatherapp.R

@BindingAdapter("setSrcFromIcon")
fun ImageView.setSrcFromIcon(img_id:String){
    setImageResource(when(img_id){
        "01d"->R.drawable.icon_sunny
        "02d"->R.drawable.icon_partly_cloudy
        "03d"->R.drawable.icon_cloudy
        "04d"->R.drawable.icon_cloudy
        "09d"->R.drawable.icon_pouring
        "10d"->R.drawable.icon_pouring
        "11d"->R.drawable.icon_lightning
        "13d"->R.drawable.icon_snowy
        "50d"->R.drawable.icon_fog

        "01n"->R.drawable.icon_moon
        "02n"->R.drawable.icon_partly_cloudy
        "03n"->R.drawable.icon_cloudy
        "04n"->R.drawable.icon_cloudy
        "09n"->R.drawable.icon_pouring
        "10n"->R.drawable.icon_pouring
        "11n"->R.drawable.icon_lightning
        "13n"->R.drawable.icon_snowy
        "50n"->R.drawable.icon_fog

         else->R.drawable.icon_cloudy
    })
}

@BindingAdapter("textColorFromIcon")
fun TextView.setTextColorFromIcon(img_id:String){
    setTextColor(
        when(img_id[img_id.length-1]){
            'n' ->R.color.night_color
            else->R.color.main_color
        }
    )
}
@BindingAdapter("solidShapeFromIcon")
fun View.setBackgroundToSolidFromIcon(img_id:String){
    setBackgroundColor(
        when(img_id[img_id.length-1]){
            'n' ->R.color.night_color
            else->R.color.main_color
        }
    )
}
@BindingAdapter("roundedShapeFromIcon")
fun View.setBackgroundToRoundedFromIcon(img_id:String){
    setBackgroundResource(
        when(img_id[img_id.length-1]){
            'n' ->R.drawable.shape_rounded_night
            else->R.drawable.shape_rounded
        }
    )
}
@BindingAdapter("roundedOutlineFromIcon")
fun View.setBackgroundToOutlinedRoundedFromIcon(img_id:String){
    setBackgroundResource(
        when(img_id[img_id.length-1]){
            'n' ->R.drawable.shape_outline_rect_rounded_night
            else->R.drawable.shape_outline_rect_rounded
        }
    )
}