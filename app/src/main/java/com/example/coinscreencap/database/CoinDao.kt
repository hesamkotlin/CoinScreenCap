package com.example.coinscreencap.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update

@Dao
interface CoinDao {
    @Insert
    suspend fun insert(localCoins: LocalCoins): String

    @Update
    suspend fun update(localCoins: LocalCoins)


}
