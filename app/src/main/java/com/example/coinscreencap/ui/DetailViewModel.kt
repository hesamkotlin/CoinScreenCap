package com.example.coinscreencap.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinscreencap.domain.CryptoRepository
import com.example.coinscreencap.shared.model.Coin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: CryptoRepository
) : ViewModel() {

    private val mCoin = MutableLiveData<Coin>()
    val coin: LiveData<Coin> = mCoin

    fun setCoinId(coinId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getCoin(coinId)
            mCoin.postValue(result)
            Log.d("DetailViewModel", result.toString())
        }

    }

}