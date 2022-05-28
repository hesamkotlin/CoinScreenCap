package com.example.coinscreencap.shared.model

data class Coin(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    val price: String,
    val vol24h: String,
    val change: String,
    val color :String?,
    val marketCap :String,
    val iconUrl:String,
    val btcPrice:String
    )