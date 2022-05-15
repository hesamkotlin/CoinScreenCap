package com.example.coinscreencap.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "localCoins")
data class CoinEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 1,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "symbol") val symbol: String,
    @ColumnInfo(name = "cmc_rank") val cmcRank: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "volume_24h") val volume24h: String,
    @ColumnInfo(name = "percent_change_24h") val percentageChange24h: String,
)