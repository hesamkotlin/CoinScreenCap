package com.example.coinscreencap.data.remote

import com.example.coinscreencap.data.model.Crypto
import com.example.coinscreencap.data.model.CryptoModel
import retrofit2.http.GET

interface WebService {
     @GET("cryptocurrency/listings/latest")
    suspend fun getCryptoModel() : CryptoModel
}