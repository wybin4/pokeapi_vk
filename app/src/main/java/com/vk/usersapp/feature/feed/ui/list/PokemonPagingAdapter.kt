package com.vk.usersapp.feature.feed.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vk.usersapp.databinding.PokemonListItemBinding
import com.vk.usersapp.feature.feed.model.PokemonListItem

open class PokemonPagingAdapter(
    private val onItemClick: ((item: PokemonListItem) -> Unit)?
): PagingDataAdapter<PokemonListItem, PokemonPagingAdapter.ItemViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PokemonListItemBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        item?.run {
            holder.bind(this)
            holder.itemView.setOnClickListener {
                onItemClick?.invoke(this)
            }
        }
    }

    inner class ItemViewHolder(private val binding: PokemonListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: PokemonListItem) {
            binding.title.text = pokemon.getCapName()
            binding.subtitle.text = pokemon.getId().toString()
            Glide.with(binding.avatar).load(pokemon.getImageUrl()).into(binding.avatar)
            binding.executePendingBindings()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<PokemonListItem>() {
        override fun areItemsTheSame(oldItem: PokemonListItem, newItem: PokemonListItem): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: PokemonListItem, newItem: PokemonListItem): Boolean {
            return oldItem.name == newItem.name
        }
    }
}
