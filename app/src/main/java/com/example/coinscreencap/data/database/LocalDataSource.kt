package com.example.coinscreencap.data.database

import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val coinDao: CoinDao,
    private val favoritesDao: FavoritesDao

) {
    suspend fun insertCoins(coinEntities: List<CoinEntity>) {
        coinDao.insert(coinEntities)
    }

    suspend fun insertFavorites(favoriteEntities: List<FavoritesEntity>) {
        favoritesDao.insert(favoriteEntities)
    }

    suspend fun update(coinEntity: CoinEntity) = coinDao.update(coinEntity)

    fun getCoinAndIsFavorites() = coinDao.getCoinAndIsFavorites()

    suspend fun getCoinAndIsFavorite(coinId: String) = coinDao.getCoinAndIsFavorite(coinId)

    fun addToFavorite(coinId: String) = favoritesDao.addToFavorite(coinId)

    fun deleteFromFavorite(coinId: String) = favoritesDao.deleteFromFavorite(coinId)

    suspend fun getCoinEntity(coinId: String) = coinDao.getCoinEntity(coinId)
}