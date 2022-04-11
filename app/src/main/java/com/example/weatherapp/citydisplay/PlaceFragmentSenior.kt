package com.example.weatherapp.citydisplay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.Util
import com.example.weatherapp.databinding.FragmentPlaceSeniorBinding
import com.example.weatherapp.databinding.FragmentSearchSeniorBinding
import com.example.weatherapp.search.SearchViewModel

class PlaceFragmentSenior:Fragment() {
    private lateinit var binding: FragmentPlaceSeniorBinding
    private lateinit var viewModel: PlaceViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val args = PlaceFragmentSeniorArgs.fromBundle(requireArguments())


        binding = FragmentPlaceSeniorBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        val factory = PlaceViewModel.Factory(args.weatherData)
        viewModel = ViewModelProvider(this,factory).get(PlaceViewModel::class.java)
        binding.viewModel = viewModel

        binding.executePendingBindings()

        Util.setActionBarTitle(viewModel.weatherData.cityName!!)


        return binding.root
    }
}