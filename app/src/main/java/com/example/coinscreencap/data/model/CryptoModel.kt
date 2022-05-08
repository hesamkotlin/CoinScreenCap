package com.example.coinscreencap.data.model

import com.google.gson.annotations.SerializedName

data class CryptoModel(

    @field:SerializedName("data")
    val data : Crypto,
)

