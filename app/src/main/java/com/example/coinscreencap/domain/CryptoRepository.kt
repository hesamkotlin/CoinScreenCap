package com.example.coinscreencap.domain

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.coinscreencap.data.CryptoPagingSource
import com.example.coinscreencap.data.remote.NetworkDataSource
import com.example.coinscreencap.data.remote.Resource
import com.example.coinscreencap.data.database.LocalDataSource
import com.example.coinscreencap.data.utils.mapToCoin
import com.example.coinscreencap.data.utils.mapToCoinEntity
import com.example.coinscreencap.shared.model.Coin
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CryptoRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource,
) {
    suspend fun updateCryptos() {
        val response = networkDataSource.getCryptoList()
        Log.d("CryRepository", response.toString())
        if (response is Resource.Success) {
            val coinEntities = response.data.map { it.mapToCoinEntity() }
            localDataSource.insertCoins(coinEntities)
            Resource.Success(coinEntities.map { it.mapToCoin() })
        }
    }

    suspend fun getCoins(): Flow<PagingData<Coin>>{
        val maxCount = localDataSource.getAllCoins().count()
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                CryptoPagingSource(localDataSource, maxCount)
            }
        ).flow
    }

    suspend fun getCoin(coinId: String): Coin {
        val coinEntity = localDataSource.getCoin(coinId)
        return coinEntity.mapToCoin()
    }

}