package com.example.coinscreencap.data.remote

import com.example.coinscreencap.data.model.Crypto
import retrofit2.http.GET

interface WebService {
     @GET("cryptocurrency/listings/latest")
    suspend fun getCryptoList() : List<Crypto>
}