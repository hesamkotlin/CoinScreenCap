package com.example.coinscreencap.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "localCoins")
data class LocalCoins(
    @PrimaryKey(autoGenerate = true) val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "symbol") val symbol: String,
    @ColumnInfo(name = "cmc_rank") val cmcRank: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "volume_24h") val volume24h: String,
    @ColumnInfo(name = "percent_change_24h") val percentageChange24h: String,
)