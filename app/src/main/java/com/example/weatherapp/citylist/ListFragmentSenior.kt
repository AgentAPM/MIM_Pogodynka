package com.example.weatherapp.citylist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.weatherapp.R
import com.example.weatherapp.Util
import com.example.weatherapp.databinding.FragmentListSeniorBinding
import com.example.weatherapp.network.PlaceData

class ListFragmentSenior: Fragment() {
    private lateinit var binding: FragmentListSeniorBinding
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val args = ListFragmentSeniorArgs.fromBundle(requireArguments())

        binding = FragmentListSeniorBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        val factory = ListViewModel.Factory(args.clusterData,args.firstPlaceData)
        viewModel = ViewModelProvider(this,factory).get(ListViewModel::class.java)
        binding.viewModel = viewModel

        Util.setActionBarTitle(viewModel.firstPlaceData.cityName!!)

        val adapter = PlaceListSeniorAdapter( PlaceDataListener{ place: PlaceData -> viewModel.onClickItem(place) })
        binding.rvSeniorClusterList.adapter = adapter

        viewModel.placeList.observe(viewLifecycleOwner){
            it.let{
                adapter.submitList(it)
            }
        }

        viewModel.navigateToSearchPlace.observe(viewLifecycleOwner){
            Log.d("spr","Observed place changing to $it")
            if(it!=null){
                findNavController().navigate(
                    ListFragmentSeniorDirections.seniorListToSeniorPlace(it!!)
                )
                viewModel.resetNavigateToSearchPlace()
            }
            Log.d("spr","After observed place changing to $it")
        }

        return binding.root
    }
}