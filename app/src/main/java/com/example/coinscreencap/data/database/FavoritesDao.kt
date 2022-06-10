package com.example.coinscreencap.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favorites: List<FavoritesEntity>)

    @Query("UPDATE favorites SET isFavorites = 1 WHERE coinOwnerId = :coinId")
    fun addToFavorite(coinId: String)

    @Query("UPDATE favorites SET isFavorites = 0 WHERE coinOwnerId = :coinId")
    fun deleteFromFavorite(coinId: String)
}