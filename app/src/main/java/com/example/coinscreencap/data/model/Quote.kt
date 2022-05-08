package com.example.coinscreencap.data.model

import com.google.gson.annotations.SerializedName

data class Quote(
    @field:SerializedName("USD")
    val usd : Usd
)