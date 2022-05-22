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

    @Query("SELECT * FROM local_coins WHERE rank < (:page *:pageSize) & rank > ((:page-1)*:pageSize+1)")
    suspend fun getCoins(page: Int, pageSize: Int): List<CoinEntity>


}
