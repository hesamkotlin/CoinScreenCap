package com.example.coinscreencap.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CoinEntity::class], version = 5)
abstract class CoinsDatabase : RoomDatabase() {

    abstract val coinDao: CoinDao

}