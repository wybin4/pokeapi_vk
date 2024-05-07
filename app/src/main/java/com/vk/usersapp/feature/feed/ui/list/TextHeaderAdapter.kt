package com.vk.usersapp.feature.feed.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vk.usersapp.databinding.TextHeaderBinding

class TextHeaderAdapter : RecyclerView.Adapter<TextHeaderAdapter.HeaderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TextHeaderBinding.inflate(inflater, parent, false)
        return HeaderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {}

    override fun getItemCount(): Int {
        return 1
    }

    inner class HeaderViewHolder(val binding: TextHeaderBinding) : RecyclerView.ViewHolder(binding.root)
}
