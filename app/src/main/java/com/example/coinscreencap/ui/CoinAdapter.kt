package com.example.coinscreencap.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.coinscreencap.R
import com.example.coinscreencap.databinding.ItemLinearCardBinding
import com.example.coinscreencap.shared.model.Coin

class CoinAdapter : androidx.recyclerview.widget.ListAdapter<Coin, CoinAdapter.CoinViewHolder>(


    object : DiffUtil.ItemCallback<Coin>() {
        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.rank == newItem.rank
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }
) {
    private lateinit var mOnItemClicked: (String) -> Unit
    private lateinit var mOnFavClicked: (String ) -> Unit

    fun setOnItemClickListener(onItemClicked: (String) -> Unit) {
        mOnItemClicked = onItemClicked
    }

    fun setOnFavClickedListener(onFavClickedListener:(String)->Unit){
        mOnFavClicked = onFavClickedListener
    }

    inner class CoinViewHolder(
        private val binding: ItemLinearCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(coin: Coin) {
            binding.model = coin


        }

        init {
            itemView.setOnClickListener {
                mOnItemClicked(getItem(absoluteAdapterPosition)!!.id)
            }
            itemView.findViewById<View>(R.id.iv_favorites).setOnClickListener{
                mOnFavClicked(getItem(absoluteAdapterPosition)!!.id)

            }

        }
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
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