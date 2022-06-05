package com.example.coinscreencap.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.coinscreencap.data.utils.mapToCoin
import com.example.coinscreencap.domain.CryptoRepository
import com.example.coinscreencap.shared.model.Coin
import com.example.coinscreencap.ui.util.LiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: CryptoRepository
) : ViewModel() {


    private val mNavigateToDetail = LiveEvent<String>()
    val navigateToDetail: LiveData<String> = mNavigateToDetail



    fun updateCryptos() {
        viewModelScope.launch(Dispatchers.IO) {
            val coinResponse = repository.updateCryptos()
            Log.d("success", coinResponse.toString())
        }
    }

    suspend fun getCoins(): Flow<PagingData<Coin>> {
        return repository.getCoins().cachedIn(viewModelScope)
    }

    fun navigateToDetail(coinID: String) {
        mNavigateToDetail.value = coinID
    }


    fun toggleFavorite(coinID: String)  {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.toggleFavorite(coinID)
            Log.d("result" , result.toString())


        }
    }
}
