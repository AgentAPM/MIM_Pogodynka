package com.example.weatherapp.search

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.Util
import com.example.weatherapp.databinding.FragmentSearchJuniorBinding
import com.example.weatherapp.databinding.FragmentSearchSeniorBinding

class SearchFragmentJunior : Fragment() {
    private lateinit var binding: FragmentSearchJuniorBinding
    private lateinit var viewModel:SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchJuniorBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        binding.viewModel = viewModel

        Util.setActionBarTitle("Pogodynka")

        viewModel.navigateToSearchPlace.observe(viewLifecycleOwner){
            Log.d("spr","Observed place changing to $it")
            if(it!=null){
                findNavController().navigate(
                    SearchFragmentJuniorDirections.juniorSearchToJuniorPlace(it!!)
                )
                viewModel.resetNavigateToSearchPlace()
            }
        }
        viewModel.navigateToSearchCluster.observe(viewLifecycleOwner){
            if(it!=null){
                findNavController().navigate(
                    SearchFragmentJuniorDirections.juniorSearchToJuniorList(it!!, viewModel.searchClusterFirstPlaceData!!)
                )
                viewModel.resetNavigateToSearchCluster()
            }
        }


        viewModel.showError.observe(viewLifecycleOwner){
            if(it!="") {
                Toast.makeText(context, it, Toast.LENGTH_SHORT)
                viewModel.resetShowError()
            }
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_ui_senior, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.changeToSenior -> changeUItoSenior()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun changeUItoSenior(){
        findNavController().navigate(SearchFragmentJuniorDirections.juniorSearchToSeniorSearch())
    }
}