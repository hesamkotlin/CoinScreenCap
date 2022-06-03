package com.example.coinscreencap.data.database

import androidx.room.*

@Dao
interface CoinDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(coinEntities: List<CoinEntity>)

    @Update
    suspend fun update(coinEntity: CoinEntity)

    @Query("SELECT * FROM local_coins")
    suspend fun getAllCoins(): List<CoinEntity>

    @Query("SELECT * FROM local_coins " +
            "WHERE rank < (:pageNumber * :pageSize) AND rank > ((:pageNumber - 1) * :pageSize) " +
            "ORDER BY rank")
    suspend fun getCoins(pageNumber: Int, pageSize: Int): List<CoinEntity>

    @Query("SELECT * FROM local_coins WHERE id = :coinId")
    suspend fun getCoin(coinId: String): CoinEntity


}
