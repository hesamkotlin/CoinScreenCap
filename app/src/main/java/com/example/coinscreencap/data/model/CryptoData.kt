package com.example.coinscreencap.data.model

import com.google.gson.annotations.SerializedName

data class CryptoData (
    @field:SerializedName("coins")
    val coins :List<Crypto>
        )