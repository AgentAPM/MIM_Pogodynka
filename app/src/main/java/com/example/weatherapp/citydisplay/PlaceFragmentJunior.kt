package com.example.weatherapp.citydisplay

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentPlaceJuniorBinding
import com.example.weatherapp.search.SearchFragmentJuniorDirections
import com.example.weatherapp.search.SearchFragmentSeniorDirections

class PlaceFragmentJunior:Fragment() {
    private lateinit var binding: FragmentPlaceJuniorBinding
    private lateinit var viewModel: PlaceViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val args = PlaceFragmentJuniorArgs.fromBundle(requireArguments())


        binding = FragmentPlaceJuniorBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        val factory = PlaceViewModel.Factory(args.weatherData)
        viewModel = ViewModelProvider(this,factory).get(PlaceViewModel::class.java)
        binding.viewModel = viewModel



        return binding.root
    }
}