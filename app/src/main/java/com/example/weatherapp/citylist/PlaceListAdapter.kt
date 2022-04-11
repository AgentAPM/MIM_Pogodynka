package com.example.weatherapp.citylist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ClusterItemJuniorBinding
import com.example.weatherapp.databinding.ClusterItemSeniorBinding
import com.example.weatherapp.network.PlaceData

class PlaceListSeniorAdapter(private val clickListener:PlaceDataListener)
    :ListAdapter<PlaceData,RecyclerView.ViewHolder>(PlaceDataDiffCallback()) {
    class ViewHolder(private val binding:ClusterItemSeniorBinding):RecyclerView.ViewHolder(binding.root){
        companion object{
            fun from(parent:ViewGroup): ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ClusterItemSeniorBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
        fun bind(placeData: PlaceData, clickListener: PlaceDataListener){
            binding.placeData = placeData
            binding.clickListener = clickListener
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).apply {
            bind(getItem(position),clickListener)
        }
    }
}

class PlaceListJuniorAdapter(private val clickListener:PlaceDataListener)
    :ListAdapter<PlaceData,RecyclerView.ViewHolder>(PlaceDataDiffCallback()) {
    class ViewHolder(private val binding: ClusterItemJuniorBinding):RecyclerView.ViewHolder(binding.root){
        companion object{
            fun from(parent:ViewGroup): ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ClusterItemJuniorBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
        fun bind(placeData: PlaceData, clickListener: PlaceDataListener){
            binding.placeData = placeData
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).apply {
            bind(getItem(position),clickListener)
        }
    }
}

class PlaceDataDiffCallback : DiffUtil.ItemCallback<PlaceData>() {
    override fun areItemsTheSame(oldItem: PlaceData, newItem: PlaceData): Boolean {
        return oldItem.id == newItem.id
    }
    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: PlaceData, newItem: PlaceData): Boolean {
        return oldItem == newItem
    }
}

