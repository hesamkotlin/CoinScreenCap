package com.example.coinscreencap.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

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

    @Query("SELECT * FROM local_coins WHERE isFavorites == 1 ORDER BY rank")
     fun getFavorites(): List<CoinEntity>

     @Query("UPDATE local_coins SET isFavorites = 1 WHERE id = :coinId")
     fun addToFavorite(coinId: String)

    @Query("UPDATE local_coins SET isFavorites = 0 WHERE id = :coinId")
    fun deleteFromFavorite(coinId: String)

}
