package com.vk.usersapp.feature.feed.ui.get

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vk.usersapp.databinding.TypeListItemBinding
import com.vk.usersapp.feature.feed.ui.ColorPicker

open class TypeAdapter(
    private val context: Context
) : ListAdapter<String, TypeAdapter.ItemViewHolder>(DiffCallback()), ColorPicker {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TypeListItemBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ItemViewHolder(private val binding: TypeListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.textView.text = item
            binding.textViewLayout.backgroundTintList = ColorStateList.valueOf(
                getColor(context, item.lowercase())
            )
            binding.executePendingBindings()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}
