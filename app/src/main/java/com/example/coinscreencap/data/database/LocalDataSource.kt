package com.example.coinscreencap.data.database

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val coinDao: CoinDao

) {
    suspend fun insertCoins(coinEntities: List<CoinEntity>) {
        coinDao.insert(coinEntities)
    }

    suspend fun update(coinEntity: CoinEntity) = coinDao.update(coinEntity)

    suspend fun getAllCoins() = coinDao.getAllCoins()

    suspend fun getCoins(pageNumber: Int, pageSize: Int): List<CoinEntity> =
        coinDao.getCoins(pageNumber, pageSize)

    suspend fun getCoin(coinId: String): CoinEntity = coinDao.getCoin(coinId)

    suspend fun getFavorites() = coinDao.getFavorites()

    suspend fun addToFavorite(coinId: String)  = coinDao.addToFavorite(coinId)

    suspend fun deleteFromFavorite(coinId: String) = coinDao.deleteFromFavorite(coinId)

}