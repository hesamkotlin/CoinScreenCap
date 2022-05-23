package com.example.coinscreencap.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CoinEntity::class], version = 2)
abstract class CoinsDatabase : RoomDatabase() {

    abstract val coinDao: CoinDao

}