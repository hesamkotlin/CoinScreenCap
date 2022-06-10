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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: CryptoRepository
) : ViewModel() {

    private val mCoin = MutableLiveData <Flow<List<Coin>>>()
    val coin: LiveData<Flow<List<Coin>>> = mCoin

    fun getFavorites() : Flow<List<Coin>>{
       return repository.getFavorites()
    }
    fun toggleFavorite(coinID: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.toggleFavorite(coinID)
            Log.d("result", result.toString())
        }
    }
}