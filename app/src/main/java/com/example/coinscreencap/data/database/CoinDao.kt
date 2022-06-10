package com.example.coinscreencap.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(coinEntities: List<CoinEntity>)

    @Update
    suspend fun update(coinEntity: CoinEntity)

    @Query("SELECT * FROM local_coins WHERE id = :coinId")
    suspend fun getCoinEntity(coinId: String) : CoinEntity

    @Transaction
    @Query("SELECT * FROM local_coins ORDER BY rank")
    fun getCoinAndIsFavorites() : Flow<List<CoinAndFavorite>>

    @Transaction
    @Query("SELECT * FROM local_coins WHERE id = :coinId")
    suspend fun getCoinAndIsFavorite(coinId: String) : CoinAndFavorite

}
