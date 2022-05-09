package com.example.coinscreencap.shared.model

import com.example.coinscreencap.data.model.Crypto

fun Crypto.mapToCoins(): Coins {
    return Coins(
        id = id,
        name = name,
        symbol = symbol,
        cmcRank = cmcRank,
        price = quote.price,
        vol24h = quote.volume24h,
        percentageChange24h = quote.percentChange24h,
    )
}