package com.example.coinscreencap.data.model

import com.google.gson.annotations.SerializedName

data class Crypto(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("symbol")
    val symbol: String,

    @field:SerializedName("cmc_rank")
    val cmcRank: String,

    @field:SerializedName("quote")
    val quote: Quote,
)