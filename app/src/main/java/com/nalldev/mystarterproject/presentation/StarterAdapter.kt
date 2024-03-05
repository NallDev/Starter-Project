package com.nalldev.mystarterproject.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nalldev.mystarterproject.data.model.StartedModel
import com.nalldev.mystarterproject.databinding.ActivityMainBinding

class StarterAdapter : PagingDataAdapter<StartedModel, StarterAdapter.ViewHolder>(StarterDiffCallback) {

    inner class ViewHolder(private val binding : ActivityMainBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : StartedModel) {

        }
    }

    object StarterDiffCallback : DiffUtil.ItemCallback<StartedModel>() {
        override fun areItemsTheSame(oldItem: StartedModel, newItem: StartedModel) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: StartedModel, newItem: StartedModel) = oldItem == newItem

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
}