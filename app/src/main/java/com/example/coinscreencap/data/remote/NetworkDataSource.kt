package com.example.coinscreencap.data.remote


import com.example.coinscreencap.shared.model.Coins
import com.example.coinscreencap.shared.model.mapToCoins
import javax.inject.Inject

class NetworkDataSource @Inject constructor(
    private val webService: WebService
) {
    suspend fun getCryptoList(): Resource<List<Coins>> {
        return try {
                val cryptoResponseList = webService.getCryptoList()
                Resource.Success(cryptoResponseList.map { it.mapToCoins() })
            } catch (e: Exception) {
                Resource.Failure(e)
            }
    }
}
