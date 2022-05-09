package com.example.coinscreencap.domain

import com.example.coinscreencap.data.remote.NetworkDataSource
import com.example.coinscreencap.data.remote.Resource
import com.example.coinscreencap.shared.model.Coins
import javax.inject.Inject

class CryptoRepository @Inject constructor(private val networkDataSource: NetworkDataSource){
    suspend fun getCryptoList(): Resource<List<Coins>>{
        return networkDataSource.getCryptoList()
    }
}