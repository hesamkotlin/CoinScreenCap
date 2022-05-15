package com.example.coinscreencap.data.database

import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val coinDao: CoinDao
) {
   suspend fun insertCoins(coinEntities: List<CoinEntity>){
        coinDao.insert(coinEntities)
    }
}