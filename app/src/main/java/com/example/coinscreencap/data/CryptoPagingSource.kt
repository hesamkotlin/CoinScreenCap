package com.example.coinscreencap.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.coinscreencap.data.database.LocalDataSource
import com.example.coinscreencap.data.utils.mapToCoin
import com.example.coinscreencap.shared.model.Coin

class CryptoPagingSource(
    private val localDataSource: LocalDataSource,
    private val maxCount: Int
) : PagingSource<Int, Coin>() {

    companion object {
        private const val PAGE_SIZE = 15
    }

    override fun getRefreshKey(state: PagingState<Int, Coin>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Coin> {
        val pageNumber: Int = if (params is LoadParams.Refresh) {
            1
        } else {
            params.key ?: 1
        }
        return try {
            val coins = localDataSource.getCoins(pageNumber, pageSize = PAGE_SIZE)
            LoadResult.Page(
                coins.map { it.mapToCoin() },
                if (pageNumber > 1) pageNumber - 1 else null,
                if(pageNumber * PAGE_SIZE >= maxCount) null else pageNumber + 1
            )
        }catch (e: Exception){
            Log.d("CryptoPagingSource", e.toString())
            LoadResult.Error(e)
        }
    }
}