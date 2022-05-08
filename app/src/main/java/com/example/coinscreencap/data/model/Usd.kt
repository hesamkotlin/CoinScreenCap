package com.example.coinscreencap.data.model

import com.google.gson.annotations.SerializedName

data class Usd(
    @field:SerializedName("price")
    val price: String,
    @field:SerializedName("volume_24h")
    val volume24h: String,
    @field:SerializedName("percent_change_24h")
    val percentChange24h: String,

)