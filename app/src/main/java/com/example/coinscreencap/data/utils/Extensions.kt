package com.example.coinscreencap.data.utils

import com.example.coinscreencap.data.database.CoinEntity
import com.example.coinscreencap.data.database.CoinAndFavorite
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
        btcPrice = btcPrice
    )
}

fun CoinAndFavorite.mapToCoin(): Coin {

    return Coin(
        id = coinEntity.id,
        name = coinEntity.name,
        symbol = coinEntity.symbol,
        rank = coinEntity.rank,
        price = coinEntity.price.round("#.##"),
        vol24h = coinEntity.volume24h,
        change = coinEntity.change.toFloat(),
        btcPrice = coinEntity.btcPrice.round("#.########"),
        color = coinEntity.color,
        iconUrl = coinEntity.iconUrl,
        marketCap = coinEntity.marketCap,
        isFavorites = favoritesEntity.isFavorites
    )
}

fun String.round(format: String) :String {
    val df = DecimalFormat(format)
    df.roundingMode = RoundingMode.DOWN
    val floatPrice = toFloat()
    return df.format(floatPrice)
}