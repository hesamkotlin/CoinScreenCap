package com.example.coinscreencap.data.database

import androidx.room.Embedded
import androidx.room.Relation

data class CoinAndFavorite(
    @Embedded val coinEntity: CoinEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "coinOwnerId"
    )
    val favoritesEntity: FavoritesEntity
)