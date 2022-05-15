package com.example.coinscreencap.data.remote


import com.example.coinscreencap.data.model.Crypto
import javax.inject.Inject

class NetworkDataSource @Inject constructor(
    private val webService: WebService
) {
    suspend fun getCryptoList(): Resource<List<Crypto>> {
        return try {
                val cryptoResponseList = webService.getCryptoModel().data
                Resource.Success(cryptoResponseList)
            } catch (e: Exception) {
                Resource.Failure(e)
            }
    }
}
