package com.example.coinscreencap.data.utils

import com.example.coinscreencap.data.database.CoinEntity
import com.example.coinscreencap.data.model.Crypto
import com.example.coinscreencap.shared.model.Coin
import java.math.RoundingMode
import java.text.DecimalFormat

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
        isFavorites = false

    )
}

fun CoinEntity.mapToCoin(): Coin {

    return Coin(
        id = id,
        name = name,
        symbol = symbol,
        rank = rank,
        price = price.round("#.##"),
        vol24h = volume24h,
        change = change.toFloat(),
        btcPrice = btcPrice.round("#.########"),
        color = color,
        iconUrl = iconUrl,
        marketCap = marketCap,
        isFavorites = false
    )
}

fun String.round(format: String) :String {
    val df = DecimalFormat(format)
    df.roundingMode = RoundingMode.DOWN
    val floatPrice = toFloat()
    return df.format(floatPrice)
}