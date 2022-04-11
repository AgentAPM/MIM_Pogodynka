package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Util.activity = this
        Util.resources = resources

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setContentView(R.layout.activity_main_senior)
    }
}
