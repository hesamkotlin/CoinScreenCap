package com.example.coinscreencap.domain

import android.util.Log
import com.example.coinscreencap.data.database.FavoritesEntity
import com.example.coinscreencap.data.remote.NetworkDataSource
import com.example.coinscreencap.data.remote.Resource
import com.example.coinscreencap.data.database.LocalDataSource
import com.example.coinscreencap.data.utils.mapToCoin
import com.example.coinscreencap.data.utils.mapToCoinEntity
import com.example.coinscreencap.shared.model.Coin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
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
            localDataSource.insertFavorites(coinEntities.map { FavoritesEntity(
                coinOwnerId = it.id,
                isFavorites = false
            ) })
        }
    }

    fun getCoins(): Flow<List<Coin>> {
        return localDataSource.getCoinAndIsFavorites().map { it -> it.map { it.mapToCoin() } }
    }

    suspend fun getCoin(coinId: String): Coin {
        val coinEntity = localDataSource.getCoinAndIsFavorite(coinId)
        return coinEntity.mapToCoin()
    }

    fun getFavorites(): Flow<List<Coin>> {
        return localDataSource.getCoinAndIsFavorites()
            .map { it.filter { coinAndFavorite -> coinAndFavorite.favoritesEntity.isFavorites } }
            .map { it -> it.map { it.mapToCoin() } }
    }

    suspend fun toggleFavorite(coinId: String) {
        val coinAndFavorite = localDataSource.getCoinAndIsFavorite(coinId)
        when (coinAndFavorite.favoritesEntity.isFavorites) {
            true -> localDataSource.deleteFromFavorite(coinId)
            false -> localDataSource.addToFavorite(coinId)
        }
        val coinEntity = localDataSource.getCoinEntity(coinId)
        localDataSource.insertCoins(listOf(coinEntity))
    }

}