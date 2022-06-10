package com.example.coinscreencap.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorites")
data class FavoritesEntity(
    @PrimaryKey val coinOwnerId: String,
    @ColumnInfo(name = "isFavorites") val isFavorites: Boolean = false

)