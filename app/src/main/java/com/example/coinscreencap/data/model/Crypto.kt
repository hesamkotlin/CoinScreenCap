package com.example.coinscreencap.data.model

import com.google.gson.annotations.SerializedName

data class Crypto(
    @field:SerializedName("uuid")
    val id: String,

    @field:SerializedName("color")
    val color: String?,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("symbol")
    val symbol: String,

    @field:SerializedName("rank")
    val rank: Int,

    @field:SerializedName("24hVolume")
    val volume24h: String,

    @field:SerializedName("btcPrice")
    val btcPrice: String,

    @field:SerializedName("change")
    val change: String,

    @field:SerializedName("iconUrl")
    val iconUrl: String,

    @field:SerializedName("marketCap")
    val marketCap: String,

    @field:SerializedName("price")
    val price: String

)