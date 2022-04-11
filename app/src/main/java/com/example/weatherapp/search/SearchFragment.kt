package com.example.weatherapp.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.R
import com.example.weatherapp.search.SearchViewModel
/*
class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val layoutID = when(true){
            true -> R.layout.search_junior
            else -> R.layout.search_senior
        }
        val view = inflater.inflate(layoutID, container, false)
        val fieldCityName = view.findViewById<TextView>(R.id.city_name_input)

        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        viewModel.navigateToSearchPlace.observe(viewLifecycleOwner) {
            if (it != null) {
                Log.d("test", "To cityDisplay of $it")
                viewModel.resetNavigateToSearchPlace()
            }
        }
        viewModel.navigateToSearchCluster.observe(viewLifecycleOwner) {
            if (it != null) {
                Log.d("test", "To cityDisplay of $it")
                viewModel.resetNavigateToSearchCluster()
            }
        }

        view.findViewById<View>(R.id.search_place).setOnClickListener {
            viewModel.onClickSearchPlace()
        }
        view.findViewById<View>(R.id.search_cluster).setOnClickListener {
            viewModel.onClickSearchCluster()
        }

        return view
    }
}*/