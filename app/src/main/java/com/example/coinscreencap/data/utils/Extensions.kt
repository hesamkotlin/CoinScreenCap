package com.example.coinscreencap.data.utils

import com.example.coinscreencap.data.database.CoinEntity
import com.example.coinscreencap.data.model.Crypto
import com.example.coinscreencap.shared.model.Coin

fun Crypto.mapToCoinEntity(): CoinEntity{
    return CoinEntity(
        name = name,
        symbol = symbol,
        cmcRank = cmcRank,
        price = quote.usd.price,
        volume24h = quote.usd.volume24h,
        percentageChange24h = quote.usd.percentChange24h,
    )
}

fun CoinEntity.mapToCoin(): Coin{
    return Coin(
        id = id,
        name = name,
        symbol = symbol,
        cmcRank = cmcRank,
        price = price,
        vol24h = volume24h,
        percentageChange24h = percentageChange24h,
    )
}