package com.example.coinscreencap.data.utils

import com.example.coinscreencap.data.database.CoinEntity
import com.example.coinscreencap.data.model.Crypto
import com.example.coinscreencap.shared.model.Coin

fun Crypto.mapToCoinEntity(): CoinEntity {
    return CoinEntity(
        id = id,
        name = name,
        symbol = symbol,
        rank = rank,
        price = price,
        volume24h = volume24h,
        change = change,
        color = color,
        marketCap = marketCap,
        iconUrl = iconUrl,
        btcPrice = btcPrice,
    )
}

fun CoinEntity.mapToCoin(): Coin {
    return Coin(
        id = id,
        name = name,
        symbol = symbol,
        rank = rank,
        price = price,
        vol24h = volume24h,
        change = change,
        btcPrice= btcPrice,
        color = color,
        iconUrl = iconUrl,
        marketCap = marketCap
    )
}