package com.example.weatherapp.search

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.Util
import com.example.weatherapp.databinding.FragmentSearchSeniorBinding

class SearchFragmentSenior:Fragment() {
    private lateinit var binding:FragmentSearchSeniorBinding
    private lateinit var viewModel:SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSearchSeniorBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        binding.viewModel = viewModel

        Util.setActionBarTitle("Pogodynka")

        viewModel.navigateToSearchPlace.observe(viewLifecycleOwner){
            Log.d("spr","Observed place changing to $it")
            if(it!=null){
                findNavController().navigate(
                    SearchFragmentSeniorDirections.seniorSearchToSeniorPlace(it!!)
                )
                viewModel.resetNavigateToSearchPlace()
            }
        }
        viewModel.navigateToSearchCluster.observe(viewLifecycleOwner){
            if(it!=null){
                findNavController().navigate(
                    SearchFragmentSeniorDirections.seniorSearchToSeniorList(it!!, viewModel.searchClusterFirstPlaceData!!)
                )
                viewModel.resetNavigateToSearchCluster()
            }
        }

        viewModel.showError.observe(viewLifecycleOwner){
            if(it!="") {
                Toast.makeText(context, it, Toast.LENGTH_LONG)
                viewModel.resetShowError()
            }
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_ui_junior, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.changeToJunior -> changeUItoJunior()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun changeUItoJunior(){
        findNavController().navigate(SearchFragmentSeniorDirections.seniorSearchToJuniorSearch())
    }
}