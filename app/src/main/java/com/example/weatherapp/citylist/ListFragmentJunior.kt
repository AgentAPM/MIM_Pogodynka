package com.example.weatherapp.citylist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.Util
import com.example.weatherapp.databinding.FragmentListJuniorBinding
import com.example.weatherapp.network.PlaceData

class ListFragmentJunior: Fragment() {
    private lateinit var binding: FragmentListJuniorBinding
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val args = ListFragmentJuniorArgs.fromBundle(requireArguments())

        binding = FragmentListJuniorBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        val factory = ListViewModel.Factory(args.clusterData,args.firstPlaceData)
        viewModel = ViewModelProvider(this,factory).get(ListViewModel::class.java)
        binding.viewModel = viewModel

        Util.setActionBarTitle(viewModel.firstPlaceData.cityName!!)

        val adapter = PlaceListJuniorAdapter( PlaceDataListener { place: PlaceData ->
            viewModel.onClickItem(place)
        })
        binding.rvSeniorClusterList.adapter = adapter

        viewModel.placeList.observe(viewLifecycleOwner){
            it.let{
                adapter.submitList(it)
            }
        }

        viewModel.navigateToSearchPlace.observe(viewLifecycleOwner){
            if(it!=null){
                findNavController().navigate(
                    ListFragmentJuniorDirections.juniorListToJuniorPlace(it!!)
                )
                viewModel.resetNavigateToSearchPlace()
            }
        }

        return binding.root
    }
}