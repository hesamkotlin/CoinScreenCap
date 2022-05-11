package com.example.coinscreencap.di

import android.content.Context
import androidx.room.Room
import com.example.coinscreencap.database.CoinsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CoinsDatabase {
        return Room.databaseBuilder(
            context,
            CoinsDatabase::class.java,
            "coins_database.db"
        ).build()
    }

    @Provides
    fun provideCoinDao(coinsDatabase: CoinsDatabase) = coinsDatabase.coinDao

}