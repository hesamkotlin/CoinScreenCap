package com.example.coinscreencap.data.remote

import com.example.coinscreencap.Constants
import com.example.coinscreencap.data.model.CryptoModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
     @GET("coins")
    suspend fun getCryptoModel(
         @Query(Constants.KEY) apiKey: String
    ) : CryptoModel
}