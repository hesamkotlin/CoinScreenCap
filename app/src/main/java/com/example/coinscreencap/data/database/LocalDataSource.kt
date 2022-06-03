package com.example.coinscreencap.data.database

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

}