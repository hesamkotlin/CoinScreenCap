package com.example.coinscreencap.shared.model

data class Coin(
    val id: Int,
    val name: String?,
    val symbol: String?,
    val cmcRank: String?,
    val price: String?,
    val vol24h: String?,
    val percentageChange24h: String?,
    )