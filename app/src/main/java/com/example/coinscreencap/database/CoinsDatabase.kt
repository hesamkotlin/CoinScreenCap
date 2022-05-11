package com.example.coinscreencap.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalCoins::class], version = 1)
abstract class CoinsDatabase : RoomDatabase() {

    abstract val coinDao: CoinDao

}