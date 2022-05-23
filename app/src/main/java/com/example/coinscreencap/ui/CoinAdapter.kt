package com.example.coinscreencap.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coinscreencap.R
import com.example.coinscreencap.databinding.ItemLinearCardBinding
import com.example.coinscreencap.shared.model.Coin

class CoinAdapter : PagingDataAdapter<Coin, CoinAdapter.CoinViewHolder>(
    object : DiffUtil.ItemCallback<Coin>() {
        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }
) {
    inner class CoinViewHolder(
        private val binding: ItemLinearCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(coin: Coin) {
            binding.model = coin
        }
    }

    override fun onBindViewHolder(holder: CoinViewHolder , position : Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding = DataBindingUtil.inflate<ItemLinearCardBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_linear_card,
            parent,
            false
        )
        return CoinViewHolder(binding)
    }
}