package com.example.coinscreencap.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "local_coins")
data class CoinEntity(
    @PrimaryKey val id :String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "symbol") val symbol: String,
    @ColumnInfo(name = "rank") val rank: Int,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "volume_24h") val volume24h: String,
    @ColumnInfo(name = "change") val change: String,
    @ColumnInfo(name = "color") val color: String?,
    @ColumnInfo(name = "btcPrice") val btcPrice: String,
    @ColumnInfo(name = "marketCap") val marketCap: String,
    @ColumnInfo(name = "iconUrl") val iconUrl: String,
)