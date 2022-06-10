package com.example.coinscreencap.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.coinscreencap.R
import com.example.coinscreencap.databinding.ItemFavoritesCardBinding
import com.example.coinscreencap.shared.model.Coin

class FavoritesAdapter :
    androidx.recyclerview.widget.ListAdapter<Coin, FavoritesAdapter.FavViewHolder>(
        object : DiffUtil.ItemCallback<Coin>() {
            override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    ) {
    private lateinit var mOnFavClicked: (String) -> Unit

    fun setOnFavClickedListener(onFavClickedListener: (String) -> Unit) {
        mOnFavClicked = onFavClickedListener
    }

    inner class FavViewHolder(
        private val binding: ItemFavoritesCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(coin: Coin) {
            binding.model = coin
        }

        init {
            itemView.findViewById<View>(R.id.iv_favorites).setOnClickListener {
                mOnFavClicked(getItem(absoluteAdapterPosition)!!.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val binding = DataBindingUtil.inflate<ItemFavoritesCardBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_favorites_card,
            parent,
            false
        )
        return FavViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return currentList.count()
    }

}
