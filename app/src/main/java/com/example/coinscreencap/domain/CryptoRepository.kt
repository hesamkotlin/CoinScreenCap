package com.example.coinscreencap.domain

import android.util.Log
import com.example.coinscreencap.data.remote.NetworkDataSource
import com.example.coinscreencap.data.remote.Resource
import com.example.coinscreencap.data.database.LocalDataSource
import com.example.coinscreencap.data.utils.mapToCoin
import com.example.coinscreencap.data.utils.mapToCoinEntity
import com.example.coinscreencap.shared.model.Coin
import javax.inject.Inject

class CryptoRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource
) {
    suspend fun getCryptoList(): Resource<List<Coin>> {
        val response = networkDataSource.getCryptoList()
        Log.d("CryRepository", response.toString())
        return when (response) {
            is Resource.Failure -> Resource.Failure(response.exception)
            is Resource.Loading -> Resource.Loading()
            is Resource.Success -> {
                val coinEntities = response.data.map { it.mapToCoinEntity() }
                localDataSource.insertCoins(coinEntities)
                Resource.Success(coinEntities.map { it.mapToCoin() })
            }
        }
    }
}