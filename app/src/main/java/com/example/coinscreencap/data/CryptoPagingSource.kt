package com.example.coinscreencap.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.coinscreencap.data.database.CoinDao
import com.example.coinscreencap.data.model.Crypto
import com.example.coinscreencap.data.utils.mapToCoin
import com.example.coinscreencap.shared.model.Coin

class CryptoPagingSource(private val coinDao: CoinDao) : PagingSource<Int, Coin>() {
    override fun getRefreshKey(state: PagingState<Int, Coin>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Coin> {
        val pageNumber: Int = if (params is LoadParams.Refresh) {
            1
        } else {
            params.key ?: 1
        }
        val coins = coinDao.getCoins(pageNumber, 10)
        return LoadResult.Page(
            coins.map { it.mapToCoin() },
            if (pageNumber > 1) pageNumber - 1 else null,
            pageNumber + 1
        )
    }
}