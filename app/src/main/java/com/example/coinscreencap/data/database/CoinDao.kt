package com.example.coinscreencap.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CoinDao {
    @Insert
    suspend fun insert(coinEntities: List<CoinEntity>)

    @Update
    suspend fun update(coinEntity: CoinEntity)

    @Query("SELECT * FROM local_coins")
    suspend fun getCoins(): List<CoinEntity>


}
