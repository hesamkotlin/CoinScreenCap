package com.example.coinscreencap.data.database

import androidx.room.*

@Dao
interface CoinDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(coinEntities: List<CoinEntity>)

    @Update
    suspend fun update(coinEntity: CoinEntity)

    @Query("SELECT * FROM local_coins")
    suspend fun getCoins(): List<CoinEntity>


}
