package com.example.coinscreencap.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        CoinEntity::class,
        FavoritesEntity::class
    ],
    version = 6
)
abstract class CoinsDatabase : RoomDatabase() {

    abstract val coinDao: CoinDao

    abstract val favoriteDao: FavoritesDao

}